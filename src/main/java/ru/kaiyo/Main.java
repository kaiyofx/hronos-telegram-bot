package ru.kaiyo;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    private static final String TOKEN = "TOKEN";

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot(TOKEN));
//        System.out.println(TextUtil.countWater("We present the first Chandra X-ray Observatory (CXO) catalog of \"pulsar X-ray filaments,\" or \"misaligned outflows.\" These are linear, synchrotron radiating features powered by ultra-relativistic electrons and positrons that escape from bow shock pulsars. The filaments are misaligned with the (large) pulsar velocity, distinguishing them from the pulsar wind nebula (PWN) trail which is also often visible in CXO ACIS images. Spectral fits and morphological properties are extracted for five secure filaments and three candidates using a uniform method. We present a search of archival CXO data for linear diffuse features; the known examples are recovered and a few additional weak candidates are identified. We also report on a snapshot CXO ACIS survey of pulsars with properties similar to the filament producers, finding no new filaments, but some diffuse emission including one PWN trail. Finally, we provide an updated model for the pulsar properties required to create filaments in light of these new observations."));
//        URLConnection connection = new URL("https://arxiv.org/pdf/2410.01785").openConnection();
//        Document document = Jsoup.parse(connection.getInputStream(), "UTF-8", "https://arxiv.org/pdf/2410.01785");
//        System.out.println(document.select("#layout-container").toString());
    }


}
