package School;

import java.util.Objects;

public class School {
    private String name;
    private int degree; // درجه بندی مدرسه که بیانگر کیفیت مدرسه می باشد و  میتواند مقادیر ۱و۲و۳ باشد

    public School(String name, int degree) {
        this.name = name;
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
//متد های hash و equal اضافه شد چون از set استفاده شده است برای نگهداری لیست مدارس
    //و set برای مفایسه به این دو نیاز دارد تا اشیا تکراری در لیست قرار ندهد.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return degree == school.degree && name.equals(school.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, degree);
    }
//متد toString اضافه شد برای نمایش شی school
    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", degree=" + degree +
                '}';
    }
}
