package weapons;

public class Pistol extends Weapon
{

    public Pistol()
    {
        super(6, 8, 1, 10);
    }

    @Override
    public String getName() {
        return "Pistol";
    }
}
