package weapons;

public class SMG extends Weapon
{
    public SMG()
    {
        super(6, 30, 1, 7);
    }

    @Override
    public String getName() {
        return "SMG";
    }
}
