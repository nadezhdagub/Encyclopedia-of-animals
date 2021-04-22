package com.example.kyrs.Integration;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.core.app.ActivityScenario;

import com.example.kyrs.Information;
import com.example.kyrs.TestUrl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class InfTest {

    @Test
    public void inf1_1() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infb_type");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infb_type");
        });

        Assert.assertEquals("Род млекопитающих отряда хищных. Согласно палеонтологическим сведениям, " +
                "род медведей появился 5—6 миллионов лет назад. Первым его представителем в настоящее время " +
                "считают медведя  Самым молодым видом рода является белый медведь, который отделился от бурого " +
                "медведя примерно 200 000 лет назад. Длина: 1,3—2 м (белогрудый медведь), 1,5—1,8 м (чёрный медведь), " +
                "1,2—3 м (бурый медведь разных подвидов), 1,6—3 м (белый медведь). Масса до 450 кг у белого медведя и 750 у кадьяка. " +
                "Диплоидное число хромосом 74.", Information.result);
    }

    @Test
    public void inf2_1() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infb_area");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infb_area");
        });

        Assert.assertEquals("Ареал проживания животного широк. Он включает в себя Арктику и Антарктику, " +
                "Канаду и Аляску. В природе медведя можно встретить в Европе, Азии, на американском континенте. " +
                "Некоторые представители хищников живут в Австралии, на островах Японии, Суматре, Яве. Они обитают на равнинах и горах, " +
                "на берегах океанов, жарких и очень холодных местностях.", Information.result);
    }

    @Test
    public void inf3_1() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infb_downsizing");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infb_downsizing");
        });

        Assert.assertEquals("Численность медведей уменьшается из за изменения климата, так же из за охоты на них браконьеров.", Information.result);
    }

    @Test
    public void inf4_1() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infb_number");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infb_number");
        });

        Assert.assertEquals("Сейчас в мире около 200 000 бурых медведей. Из них большинство обитает в " +
                "России — 120 000, США — 32 500 (95 % живёт на Аляске) и Канаде — 21 750.", Information.result);
    }

    @Test
    public void inf5_1() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infb_security");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infb_security");
        });

        Assert.assertEquals("В ряде стран Западной Европы — Испании, Франции, Италии — медведи находятся под частичной " +
                "или полной охраной, в других — Югославии, Румынии, Болгарии — их добыча более или менее ограниченна и допускается " +
                "лишь в спортивных целях по платным лицензиям. Много медведей в крупных национальных парках США и Канады.", Information.result);
    }

    @Test
    public void inf1_2() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infv_type");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infv_type");
        });

        Assert.assertEquals("Вид животного общее положение. вид хищных млекопитающих из семейства псовых. Кроме того, как " +
                "показывают результаты изучения последовательности ДНК и дрейфа генов, является прямым предком домашней собаки, " +
                "которая обычно рассматривается как подвид волка Волк — одно из самых крупных современных животных в своём семействе: " +
                "длина его тела (без учёта хвоста) может достигать 160 см, длина хвоста — до 52 см, высота в холке — до 90 см; масса тела " +
                "может доходить до 90—102 кг.", Information.result);
    }

    @Test
    public void inf2_2() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infv_area");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infv_area");
        });

        Assert.assertEquals("Волк обитает в самых разных ландшафтах, но предпочитает степи, полупустыни, тундру, лесостепь, " +
                "избегая густых лесных массивов. В горах распространён от подножья до области альпийских лугов, придерживаясь открытых, слабо " +
                "пересечённых участков. Может селиться недалеко от человеческого жилья. В таёжной зоне распространился вслед за человеком, по мере " +
                "вырубки тайги.", Information.result);
    }

    @Test
    public void inf3_2() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infv_downsizing");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infv_downsizing");
        });

        Assert.assertEquals("В настоящее время его ареал и общая численность животных заметно уменьшились, главным образом в результате " +
                "человеческой деятельности: изменения природных ландшафтов, урбанизации и массового истребления. Во многих регионах мира волк находится " +
                "на грани полного исчезновения, хотя на севере Евразии и Америки его популяция всё ещё остаётся стабильной. Несмотря на то, что популяция " +
                "волков продолжает уменьшаться, он до сих пор во многих местах является объектом охоты как представляющий потенциальную опасность для " +
                "человека и домашнего скота либо ради развлечения.", Information.result);
    }

    @Test
    public void inf4_2() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infv_number");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infv_number");
        });

        Assert.assertEquals("Всего выделяют примерно 32 подвида волка, различающихся размерами и оттенками меха. На данный момент общая численность волков " +
                "составляет 60-70 тысяч особей.", Information.result);
    }

    @Test
    public void inf5_2() {

        TestUrl testUrl = new TestUrl();

        testUrl.setUrl("http://localhost:8080/animal/infv_security");
        ActivityScenario<Information> scenario =
                ActivityScenario.launch(Information.class);
        scenario.onActivity(activity -> {
            testUrl.setUrl("http://localhost:8080/animal/infv_security");
        });

        Assert.assertEquals("Международный союз охраны природы и природных ресурсов (МСОП) финансирует проект, целью которого является" +
                " увеличение численности итальянской популяции этого хищника.  Швеция: ввела закон, по которому волк находится под охраной " +
                "государства. Ради спасения хищников стали выплачиваться денежные компенсации фермерам, пострадавшим от них. К сожалению, " +
                "большинство программ по сохранению этих животных не имеют средств для борьбы с аферистами, часто встречающиеся. ", Information.result);
    }


}
