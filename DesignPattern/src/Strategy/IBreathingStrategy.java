package Strategy;

interface IBreathingStrategy {
    void Breathe();
}

class BreatheViaSkin implements IBreathingStrategy
{
    @Override
    public void Breathe() {
        System.out.println("Breathing via skin");
    }
}

class BreatheViaGills implements IBreathingStrategy
{
    @Override
    public void Breathe()
    {
        System.out.println("Breathing via Gills");
    }
}

class BreatheViaLungs implements IBreathingStrategy
{
    @Override
    public void Breathe()
    {
        System.out.println(" Breathing via Lungs");
    }
}