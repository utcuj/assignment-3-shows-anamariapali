package Bridge;

import model.AddShow;
import model.Shows;

public
class ShowBridge extends ShowAbr {

    public ShowBridge(AddShow addShow) {
        super(addShow);
    }

    public void add(Shows show) {
        addShow.add(show);
    }
}
