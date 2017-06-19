package org.drtshock;

/**
 * A type of food added to tubers.
 */
class Condiment {
    private final String name;
    private final boolean delicious;

    public Condiment(String name, boolean delicious) {
        this.name = name;
        this.delicious = delicious;
    }

    /**
     * Returns if this condiment is delicious or not.
     *
     * @return true if delicious, false if otherwise
     */
    public boolean isDelicious() {
        return this.delicious;
    }

    /**
     * Gets the name of this condiment.
     *
     * @return Name
     */
    public String getName() {
        return this.name;
    }
}