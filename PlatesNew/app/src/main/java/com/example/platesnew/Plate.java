package com.example.platesnew;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Plate {
    String plate;
    String region;


    public Plate(String plate) {
        this.plate= plate;
    }

    public String getPlate() {
        return plate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(){

            switch (plate) {

                case "01" :
                case "101" : region = "Республика Адыгея";
                    break;

                case "02" :
                case "102" :
                case "702" :region =("Республика Башкортостан");
                    break;

                case "03" :region =("Республика Бурятия");
                    break;

                case "04" :region =("Республика Алтай");
                    break;

                case "05" :region =("Республика Дагестан");
                    break;

                case "06" :region =("Республика Ингушетия");
                    break;

                case "07" :region =("Кабардино-Балкарская Республика");
                    break;

                case "08" :region =("Республика Калмыкия");
                    break;

                case "09" :region =("Карачаево-Черкесская Республика");
                    break;

                case "10" :region =("Республика Карелия");
                    break;

                case "11" :region =("Республика Коми");
                    break;

                case "12" :region =("Республика Марий Эл");
                    break;

                case "13" :
                case "113" :region =("Республика Мордовия");
                    break;

                case "14" :region =("Республика Саха (Якутия)");
                    break;

                case "15" :region =("Республика Северная Осетия — Алания");
                    break;

                case "16" :
                case "116" :
                case "716" :region =("Республика Татарстан");
                    break;

                case "17" :region =("Республика Тыва");
                    break;

                case "18" :region =("Удмуртская Республика");
                    break;

                case "19" :region =("Республика Хакасия");
                    break;

                case "21" :
                case "121" :region =("Республика Чувашия");
                    break;

                case "22" :
                case "122" :region =("Алтайский край");
                    break;


                case "23" :
                case "93" :
                case "123" :
                case "193" :region =("Краснодарский край");
                    break;

                case "24" :
                case "124" :region =("Красноярский край");
                    break;

                case "25" :
                case "125" :region =("Приморский край");
                    break;

                case "26" :
                case "126" :region =("Ставропольский край");
                    break;

                case "27" :region =("Хабаровский край");
                    break;

                case "28" :region =("Амурская область");
                    break;

                case "29" :region =("Архангельская область");
                    break;

                case "30" :region =("Астраханская область");
                    break;

                case "31" :region =("Белгородская область");
                    break;

                case "32" :region =("Брянская область");
                    break;

                case "33" :region =("Владимирская область");
                    break;

                case "34" :
                case "134" :region =("Волгоградская область");
                    break;

                case "35" :region =("Вологодская область");
                    break;

                case "36" :
                case "136" :region =("Воронежская область");
                    break;

                case "37" :region =("Ивановская область");
                    break;

                case "38" :
                case "138" :region =("Иркутская область");
                    break;

                case "39" :region =("Калининградская область");
                    break;

                case "40" :region =("Калужская область");
                    break;

                case "41" :region =("Камчатский край");
                    break;

                case "42" :
                case "142" :region =("Кемеровская область");
                    break;

                case "43" :region =("Кировская область");
                    break;

                case "44" :region =("Костромская область");
                    break;

                case "45" :region =("Курганская область");
                    break;

                case "46" :region =("Курская область");
                    break;

                case "47" :
                case "147" :region =("Ленинградская область");
                    break;

                case "48" :region =("Липецкая область");
                    break;

                case "49" :region =("Магаданская область");
                    break;

                case "50" :
                case "90" :
                case "150" :
                case "190" :
                case "750" :
                case "790" :region =("Московская область");
                    break;

                case "51" :region =("Мурманская область");
                    break;

                case "52" :
                case "152" :region =("Нижегородская область");
                    break;

                case "53" :region =("Новгородская область");
                    break;

                case "54" :
                case "154" :region =("Новосибирская область");
                    break;

                case "55" :region =("Омская область");
                    break;

                case "56" :
                case "156" :region =("Оренбургская область");
                    break;

                case "57" :region =("Орловская область");
                    break;

                case "58" :region =("Пензенская область");
                    break;

                case "59" :
                case "159" :region =("Пермский край");
                    break;

                case "60" :region =("Псковская область");
                    break;

                case "61" :
                case "161" :
                case "761" :region =("Ростовская область");
                    break;

                case "62" :region =("Рязанская область");
                    break;

                case "63" :
                case "163" :
                case "763" :region =("Самарская область");
                    break;

                case "64" :
                case "164" :region =("Саратовская область");
                    break;

                case "65" :region =("Сахалинская область");
                    break;

                case "66" :
                case "96" :
                case "196" :region =("Свердловская область");
                    break;

                case "67" :region =("Смоленская область");
                    break;

                case "68" :region =("Тамбовская область");
                    break;

                case "69" :region =("Тверская область");
                    break;

                case "70" :region =("Томская область");
                    break;

                case "71" :region =("Тульская область");
                    break;

                case "72" :region =("Тюменская область");
                    break;

                case "73" :
                case "173" :region =("Ульяновская область");
                    break;

                case "74" :
                case "174" :
                case "774" :region =("Челябинская область");
                    break;

                case "75" :region =("Забайкальский край");
                    break;

                case "76" :region =("Ярославская область");
                    break;

                case "77" :
                case "97" :
                case "99" :
                case "177" :
                case "197" :
                case "199" :
                case "777" :
                case "797" :
                case "799" :region =("Москва");
                    break;

                case "78" :
                case "98" :
                case "178" :
                case "198" :region =("Санкт-Петербург");
                    break;

                case "79" :region =("Еврейская автономная область");
                    break;

                case "82" :region =("Республика Крым");
                    break;


                case "83" :region =("Ненецкий автономный округ");
                    break;

                case "86" :
                case "186" :region =("Ханты-Мансийский автономный округ — Югра");
                    break;

                case "87" :region =("Чукотский автономный округ");
                    break;

                case "89" :region =("Ямало-Ненецкий автономный округ");
                    break;


                case "92" :region =("Севастополь");
                    break;


                case "95" :region =("Чеченская Республика");

                    break;

                default: region =(null);
                    break;
        }

    } }
