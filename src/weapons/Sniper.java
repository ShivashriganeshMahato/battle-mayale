package weapons;

public class Sniper extends Weapon
{

    public Sniper()
    {
        super(8, 5, 1, 20);
    }

    @Override
    public String getName() {
        return "Sniper";
    }
}
