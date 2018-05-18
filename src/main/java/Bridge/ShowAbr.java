package Bridge;

import model.AddShow;
import model.Shows;

public abstract
class   ShowAbr  {
    AddShow addShow;
    public ShowAbr() {}

    public ShowAbr(AddShow addShow) {
        this.addShow = addShow;
    }

    abstract void add(Shows show);
}
