package Observable;

interface IChannel {
    public void Notify();
    public void Subscribe( ISubscriber subscriber);
    public void Remove(ISubscriber subscriber);
}