package quest;

import item.Item;

public abstract class QuestItem extends Item {

    public QuestItem(String name, String description){
        super(name, description);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getName(), getDescription());
    }

}
