package unit;

import java.util.Objects;

public class Quest {
    private String name;
    public Quest(String name){
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return Objects.equals(name, quest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean startRequirementsFullfilled(Player player){
        return true;
    }
    public String getName(){
        return name;
    }
}