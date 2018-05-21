package weapons;

public class Shotgun extends Weapon
{

    public Shotgun()
    {
        super(8, 2, 5, 10);
    }

    @Override
    public String getName() {
        return "Shotgun";
    }
}
