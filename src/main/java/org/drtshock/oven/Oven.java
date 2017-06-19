package org.drtshock.oven;

import org.drtshock.Tuber;
import org.drtshock.exceptions.BurntException;
import org.drtshock.exceptions.OvenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The gears behind creating a potato. Used to cook potatoes.
 */
public class Oven {

    public static List<Oven> ovens = new ArrayList<>();
    private List<Tuber> cooking;

    public Oven() {
        this.cooking = new ArrayList<>();
    }

    /**
     * Lists the Tuber's that are being cooked.
     *
     * @return a list of Tuber's that are in the oven.
     */
    public List<Tuber> inOven() {
        return this.cooking;
    }

    /**
     * Used to cook a potato for consuming.
     *
     * @param tuber the potato that is in need of cooking.
     *
     * @throws OvenException If the oven has trouble cooking the potato.
     * @throws BurntException If the potato becomes burnt.
     */
    public void cook(Tuber tuber, int degrees, int minutes) throws OvenException, BurntException {
        this.cooking.add(tuber);

        new Thread(() -> {
            Timer schedule = new Timer();

            TimerTask temperature = new TimerTask() {
                @Override
                public void run() {
                    tuber.raiseTemperature(1);
                }
            };

            schedule.schedule(new TimerTask() {
                @Override
                public void run() {
                    temperature.cancel();

                }
            }, minutes * 1000);

            schedule.scheduleAtFixedRate(temperature, 0, 1000);
        });
    }

}
