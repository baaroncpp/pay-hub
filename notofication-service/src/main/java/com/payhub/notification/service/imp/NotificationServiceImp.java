package com.payhub.notification.service.imp;

import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.Notification;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;
import com.payhub.notification.network.RetrofitSkylineService;
import com.payhub.notification.network.SkylineAPIService;
import com.payhub.notification.service.EmailService;
import com.payhub.notification.service.NotificationService;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.mail.MessagingException;
import java.io.IOException;

//@Slf4j
@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private SkylineAPIService skylineAPIService;

    @Autowired
    private EmailService emailService;

    @Value("${skyline.sms.token}")
    private String token;

    @Value("${message.source.name}")
    private String fromName;

    @Override
    public SmsResponse sendSms(Notification notification) throws IOException {

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<SmsResponse> call = skylineAPIService.sendSms(token, notification.getAddress(), fromName, notification.getContent());

        String url = call.request().url().toString();
        //log.info("request url: "+url);

        Response<SmsResponse> response = call.execute();
        SmsResponse smsResponse = response.body();

        return smsResponse;
    }

    @Override
    public void sendEmail(Notification notification) throws MessagingException {

        if(notification.getAttachmentPath().isEmpty()){
            emailService.sendSimpleEmail(notification.getAddress(), notification.getSubject(), notification.getContent());
        }else{
            emailService.sendMailWithAttachment(notification.getAddress(), notification.getSubject(), notification.getContent(), notification.getAttachmentPath());
        }

    }

    @Override
    public ReportResponse getDeliveryReport(String reference) throws IOException {

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<ReportResponse> call = skylineAPIService.deliveryReports(token, reference);

        Response<ReportResponse> response = call.execute();
        ReportResponse reportResponse = response.body();

        return reportResponse;
    }

    @Override
    public BalanceResponse getBalance() throws IOException {

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<BalanceResponse> call = skylineAPIService.checkBalance(token);

        Response<BalanceResponse> response = call.execute();
        BalanceResponse balanceResponse = response.body();

        return balanceResponse;
    }
}
