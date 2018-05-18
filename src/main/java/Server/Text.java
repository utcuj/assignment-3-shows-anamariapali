package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public
class Text implements Serializable {
    private static final long serialVersionUID = 1L;

    public String methodName;

    public List<Object[]> data = new ArrayList<Object[]>();

}
