package deadwood.model.areas;

import java.util.ArrayList;

import deadwood.model.*;

public class Set extends Area{
    public Set(String name, ArrayList<Area> neighbors, Role[] roles) {
        super(name, neighbors, roles);
    }

    public Set(){
        super();
    }
}
