package chap02;

public class Greeter {

    private String format;

    public String greet(String greet) {
        return String.format(format, greet);
    }

    public void setFormat(String format){
        this.format = format;
    }
}
