package Observable;

import java.util.*;

public class Subscriber implements ISubscriber {

    private Channel channel;
    private String UserId;
    private List<String> videos = new ArrayList<>();

    Subscriber(String UserId)
    {
        this.UserId = UserId;
    }

    public String getUserId() {
        return UserId;
    }

    @Override
    public void Update() {
        videos.add( channel.getLatestVideo() );
        System.out.println("Channel " + channel.getChannelName() + " posted new video " + channel.getLatestVideo() + " UserName = " + UserId );
    }

    public void subscribe(Channel channel)
    {
        this.channel = channel;
        channel.Subscribe(this);
    }
}

