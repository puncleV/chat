/**
 * Created by dart on 01.09.15.
 */
public class Message {
    private String _author;
    private String _text;
    public Message(String author, String text){
        this._author = author;
        this._text = text;
    }
    public String get_author() {
        return _author;
    }

    public void set_author(String _author) {
        this._author = _author;
    }

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }
}
