package stickman.entity;

public interface EntityPrototyper {

    /**
     * A default Entity deep copy. Certain Entity implementor classes may need a specific method with
     * custom paramaters to enable their implementation specific copy.
     * But otherwise, this enables prototyping - specific entities can be copied, since they will
     * all have to implement this method.
     * @return a Deep Copy of this entity.
     */
    Entity copy();
}
