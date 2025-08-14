package Observable;
import java.util.*;

public class Channel implements IChannel{

    private List<ISubscriber> subscribers = new ArrayList<>();
    private String latestVideo;
    private String channelName;

    Channel(String channelName)
    {
        this.channelName = channelName;
    }

    public void addVideo(String title)
    {
        // businsess logic for Adding a new video
        this.latestVideo = title;
        Notify();
    }

    public String getLatestVideo() {
        return latestVideo;
    }

    public String getChannelName()
    {
        return channelName;
    }

    @Override
    public void Notify() {
        for( ISubscriber s : subscribers )
            s.Update();
    }

    @Override
    public void Subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void Remove(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
