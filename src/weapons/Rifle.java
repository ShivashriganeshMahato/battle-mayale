package weapons;

public class Rifle extends Weapon
{

    public Rifle()
    {
        super(6, 20, 1, 8);
    }

    @Override
    public String getName() {
        return "Rifle";
    }
}
