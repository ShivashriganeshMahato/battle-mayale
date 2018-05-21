package weapons;

public class RailGun extends Weapon
{
    public RailGun()
    {
        super(10, 1, 1, 80);
    }

    @Override
    public String getName() {
        return "Rail Gun";
    }
}
