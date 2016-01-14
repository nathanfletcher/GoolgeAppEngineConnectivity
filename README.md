# GoolgeAppEngineConnectivity

<h3>Android: AndroidGAEConnection.java:</h3>
It's an Async Task with a method that uses HTTPURLConnection. Please note that HTTPClient has been depricated for Android.
Once the Async task is in your MainActivity you initialize and call it like this:
<code>AndroidGAEConnection fetch = new AndroidGAEConnection();
        fetch.execute();</code>
