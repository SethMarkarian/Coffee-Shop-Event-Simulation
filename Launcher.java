/**
 * Runs simulation
 *
 * @author Seth Markarian
 */
public class Launcher
{
    /**
     * Runs simulation with a certain amout of cashiers
     * 
     * @param i number of cashiers
     */
    public Launcher(int i) {
        CoffeeShop s = new CoffeeShop();
        s.run(i);
    }
}