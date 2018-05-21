package weapons;

public class LMG extends Weapon
{

    public LMG()
    {
        super(6, 40, 1, 6);
    }

    @Override
    public String getName() {
        return "LMG";
    }
}
