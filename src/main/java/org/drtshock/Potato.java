package org.drtshock;

import org.drtshock.exceptions.BurntException;
import org.drtshock.exceptions.OvenException;
import org.drtshock.oven.Oven;

import java.util.ArrayList;
import java.util.List;

/**
 * A delicious tuber that is eaten by various peoples all over the world.
 */
public class Potato implements Tuber {

    private final List<Condiment> condiments = new ArrayList<>();
    private boolean cooked = false;
    private boolean burnt = false;
    private int temperature = 0;

    /**
     * Creates a new potato for the eating.
     */
    public Potato() {

    }

    /**
     * Gets the condiments on this potato.
     *
     * @return Mutable list of condiments
     */
    public List<Condiment> getCondiments() {
        return this.condiments;
    }

    /**
     * Prepares the potato for consumption. Adds various condiments and prints them to stdout. Ensures that the potato
     * is delicious. If it is not, a {@link NotDeliciousException} is thrown.
     *
     * @param degrees temperature to cook the potato at.
     * @param minutes amount of minutes to cook the potato for.
     *
     * @throws NotDeliciousException If the potato is not delicious
     */
    public void prepare(int degrees, int minutes) throws NotDeliciousException {
        this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "pepper",
                "salt", "tabasco", "tomatoes");
        this.listCondiments();
        if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_BAKED);

        try {
            this.cook(degrees, minutes);
        } catch (OvenException | BurntException e) {
            System.out.println("");
        }
    }

    /**
     * Cooks the potato to perfection.
     *
     * @param degrees temperature to cook the potato at.
     * @param minutes amount of minutes to cook the potato for.
     *
     * @throws OvenException If the oven has trouble cooking the potato.
     * @throws BurntException If the potato is burnt.
     */
    public void cook(int degrees, int minutes) throws OvenException, BurntException {

    }

    /**
     * Adds condiments to the potato.
     *
     * @param names Names of the condiments to add
     */
    public void addCondiments(String... names) throws NotDeliciousException {
        for (String condimentName : names) {
            Condiment condiment = new Condiment(condimentName, true);
            if (!condiment.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_DELICIOUS_CONDIMENT);
            this.getCondiments().add(condiment);
        }
    }

    /**
     * Prints the names of the condiments on this potato to stdout.
     *
     * @see #getCondiments()
     */
    public void listCondiments() {
        for (Condiment condiment : this.getCondiments()) {
            System.out.println(condiment.getName());
        }
    }

    /**
     * Checks if this potato is baked.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean inOven() {
        return Oven.ovens.stream().anyMatch(oven -> oven.inOven().contains(this));
    }

    /**
     * Checks if this potato is cooked.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isCooked() {
        return this.cooked;
    }

    /**
     * Checks if this potato is delicious.
     *
     * @return true if this potato is delicious, false if otherwise
     */
    @Override
    public boolean isDelicious() {
        return !this.isBurnt() && (this.getTemperature() > 5 && this.getTemperature() < 75) && this.isCooked();
    }

    public boolean isBurnt() {
        return this.burnt;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void raiseTemperature(int temperature) {
        this.temperature += temperature;
    }

    /**
     * Propagates a new potato.
     *
     * @return A new potato
     */
    @Override
    public Tuber propagate() {
        return new Potato();
    }

}
