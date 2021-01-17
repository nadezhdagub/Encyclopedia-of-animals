/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class HttpTraceConfig {

    @Bean
    public AbstractRequestLoggingFilter logFilter() {
        RequestLoggingFilter filter = new RequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setAfterMessagePrefix("");
        return filter;
    }

    private static class RequestLoggingFilter extends AbstractRequestLoggingFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            final BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper(response);
            final BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(request);

            super.setIncludePayload(true);
            super.setMaxPayloadLength(2000);

            super.doFilterInternal(bufferedRequest, bufferedResponse, filterChain);

            String message = createMessage(request, "request: {", "");

            // add request body
            final String requestContent = bufferedRequest.getContent();
            if (requestContent != null) {
                message += ", payload:" + requestContent;
            }
            message += "}";

            // add response fields
            message += ", response: {status:" + response.getStatus();

            final String content = bufferedResponse.getContent();

            if (content != null) {
                message += ", content-length:" + content.length();
            }
            final String contentType = response.getContentType();
            if (contentType != null
                    && contentType.toLowerCase().startsWith(APPLICATION_JSON_VALUE.toLowerCase())) {
                message += ", content:" + content;
            }
            message += "}";

            log.debug(message);
        }

        @Override
        protected void beforeRequest(HttpServletRequest request, String message) {
        }

        @Override
        protected void afterRequest(HttpServletRequest request, String message) {
        }
    }

    public static class TeeServletOutputStream extends ServletOutputStream {

        private final TeeOutputStream targetStream;

        public TeeServletOutputStream(OutputStream one, OutputStream two) {
            targetStream = new TeeOutputStream(one, two);
        }

        @Override
        public void write(int arg0) throws IOException {
            targetStream.write(arg0);
        }

        public void flush() throws IOException {
            super.flush();
            targetStream.flush();
        }

        public void close() throws IOException {
            super.close();
            targetStream.close();
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
        }
    }

    public static class BufferedResponseWrapper implements HttpServletResponse {

        private interface HsrExclusions {
            ServletOutputStream getOutputStream() throws IOException;
        }

        @Delegate(excludes = HsrExclusions.class)
        private HttpServletResponse original;
        private TeeServletOutputStream tee;
        private ByteArrayOutputStream bos;

        public BufferedResponseWrapper(HttpServletResponse response) {
            original = response;
        }

        public String getContent() {
            return Optional.ofNullable(bos)
                    .map(Object::toString)
                    .orElse(null);
        }

        public ServletOutputStream getOutputStream() throws IOException {
            if (tee == null) {
                bos = new ByteArrayOutputStream();
                tee = new TeeServletOutputStream(original.getOutputStream(), bos);
            }
            return tee;
        }
    }

    private static class TeeServletInputStream extends ServletInputStream {

        private TeeInputStream targetStream;

        public TeeServletInputStream(InputStream one, OutputStream two) {
            targetStream = new TeeInputStream(one, two);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {
        }

        @Override
        public int read() throws IOException {
            return targetStream.read();
        }

        public void close() throws IOException {
            super.close();
            targetStream.close();
        }
    }

    private static class BufferedRequestWrapper implements HttpServletRequest {
        private interface Exclusions {
            ServletInputStream getInputStream() throws IOException;
        }

        @Delegate(excludes = Exclusions.class)
        private HttpServletRequest original;
        private TeeServletInputStream tee;
        private ByteArrayOutputStream bos;

        public BufferedRequestWrapper(HttpServletRequest request) {
            original = request;
        }

        public String getContent() {
            return Optional.ofNullable(bos)
                    .map(Object::toString)
                    .orElse(null);
        }

        public ServletInputStream getInputStream() throws IOException {
            if (tee == null) {
                bos = new ByteArrayOutputStream();
                tee = new TeeServletInputStream(original.getInputStream(), bos);
            }
            return tee;
        }
    }
}