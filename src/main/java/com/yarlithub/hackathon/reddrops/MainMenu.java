/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.reddrops;

import com.yarlithub.hackathon.util.Messages;
import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.StatusCodes;
import hms.kite.samples.api.ussd.MoUssdListener;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdResp;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mayooran
 */
public class MainMenu implements MoUssdListener{
  
    private final static Logger LOGGER = Logger.getLogger(MainMenu.class.getName());
    
    private static final String EXIT_SERVICE_CODE = "000";
    private static final String BACK_SERVICE_CODE = "999"; 
    private static final String USSD_OP_MO_INIT = "mo-init";
    private static final String USSD_OP_MT_CONT = "mt-cont";
    private static final String USSD_OP_MT_FIN = "mt-fin";
    private static final String NEXT_SERVICE_CODE = "992"; 
    private static final String MAIN_SERVICE_CODE = "994";
    
    
    private List<String> menuState = new ArrayList<String>();

    
    // service to send the request
    private UssdRequestSender ussdMtSender;
    
    public static int level=0;
    int count_level0=1;
    int count_level1=1;   
    String init_key="0"; 
    
   String display="";
    
    @Override
    public void init()
    {
        // create and initialize service
        try
        {
            ussdMtSender = new UssdRequestSender(new URL(Messages.getMessage("sdp.server.url")));
            
        }
        catch (MalformedURLException e)
        {
            LOGGER.log(Level.INFO, "Unexpected error occurred", e);
        }
    }
    
    @Override
    public void onReceivedUssd(final MoUssdReq moUssdReq)
    {
        try
        {
            // start processing request
            processRequest(moUssdReq);           
        }
        catch (SdpException e)
        {                  
            LOGGER.log(Level.INFO, "Unexpected error occurred", e);
        }
    }
    
    
    private void processRequest(final MoUssdReq moUssdReq) throws SdpException
    {
        // exit request - session destroy
        if (moUssdReq.getMessage().equals(EXIT_SERVICE_CODE))
        {
            terminateSession(moUssdReq);
            return;// completed work and return
        }

        // back button handling
        if (moUssdReq.getMessage().equals(BACK_SERVICE_CODE))
        {
            backButtonHandle(moUssdReq);  
            return;// completed work and return
        }

        // get current service code
        String serviceCode;
        if (USSD_OP_MO_INIT.equals(moUssdReq.getUssdOperation()))
        {
            serviceCode = "0";
            level=0;
            clearMenuState();
            
        }
        else
        {
            if (moUssdReq.getMessage().equals(NEXT_SERVICE_CODE))
            {
                if(level==0){
                count_level0+=1;
                }
                else if(level==1){
                    count_level1+=1;
                }      
                serviceCode = getServiceCode(moUssdReq);
            }
         else{
                serviceCode = getServiceCode(moUssdReq);
                //level=level+1;
            }
        }
        // create request to display user
        final MtUssdReq request = createRequest(moUssdReq, buildMenuContent(serviceCode), USSD_OP_MT_CONT);
        sendRequest(request);
        // record menu state
        menuState.add(serviceCode);
    }

    
    private MtUssdReq createRequest(final MoUssdReq moUssdReq, final String menuContent, final String ussdOperation)
    {
        final MtUssdReq request = new MtUssdReq();
        request.setApplicationId(moUssdReq.getApplicationId());
        request.setEncoding(moUssdReq.getEncoding());
        request.setMessage(menuContent);
        request.setPassword(Messages.getMessage(moUssdReq.getApplicationId()));
        request.setSessionId(moUssdReq.getSessionId());
        request.setUssdOperation(Messages.getMessage("operation"));
        request.setVersion(moUssdReq.getVersion());
        request.setDestinationAddress(moUssdReq.getSourceAddress());
        return request;
    }
    
    private String getText(final String key)
    {
        String outputString="";
        
        if(level==0){          
           outputString="1.Get Info\n2.Send Info\n000.Exit"; 
        }
       /* if(level>0)
        {
            if(level==1){
                init_key=key;
                if("1".equals(init_key)){
                    outputString=getInfo(key);
                }
                else if("2".equals(init_key)){
                    outputString=sendInfo(key);
                }
                else outputString=outputString+"Invalid Selection"+"\n999:Back"+key;
            }
            else
            {
                if("1".equals(init_key)){
                    outputString=getInfo(key);
                }
                else if("2".equals(init_key)){
                    outputString=sendInfo(key);
                }
                else outputString=outputString+"Invalid Selection"+"\n999:Back"+key;
            }  
        } */
       return outputString;
    }
 
    private String getInfo(final String key)
    {
        String outputString="dsfdf";
        /* 
        Market_DetailsDAO mkt=new Market_DetailsDAO();
        VegetableDAO vgt = new VegetableDAO();
        FruitDAO frt = new FruitDAO();
        OtherDAO othr = new OtherDAO();
        
        
       
        List location=mkt.getMarketLocation();
        int key_int=Integer.parseInt(key);
        
        if(level==1){
             int size_location=location.size();
             if(size_location==0){
                  outputString="No Data Found \n000.Exit";
             }
             else if(location.size()>5){
                int j=0;
                if(count_level0*5 < size_location){
                    j=count_level0*5;
                }
                else{
                    j=size_location;
                }
                for(int i=(count_level0-1)*5;i<j;i++){
                    outputString= outputString + (i+1)+ ":" + location.get(i).toString()+"\n";
                } 
                outputString=outputString+"999:Back\n992.Next\n000.Exit";
                }
             
             else{
                for(int i=0;i<size_location;i++)
                     outputString= outputString + (i+1)+ ":" + location.get(i).toString()+"\n";
                outputString=outputString+"000.Exit";
             }
           
        }
        else if(level==2){
           
            if(key_int<=location.size() && key_int>0){ 
            loc=location.get(key_int-1).toString();  
            market=mkt.getMarket(location.get(key_int-1).toString());
            int size_market=market.size();
            
             if(size_market==0){
                 outputString="No Data Found \n 999:Back\n000.Exit";
             }
             if(market.size()>5){
                   
                    int j=0;
                    if(count_level1*5 < size_market){
                        j=count_level1*5;
                    }
                    else{
                        j=size_market;
                    }
                    for(int i=(count_level1-1)*5;i<j;i++){
                        outputString= outputString + (i+1)+ ":" + market.get(i).toString()+"\n";
                    }
                    outputString=outputString+"999:Back\n000.Exit";
            }
             else{
                for(int i=0; i<market.size();i++)
                    outputString= outputString + (i+1)+ ":" + market.get(i).toString()+"\n";
              outputString=outputString+"999:Back\n000.Exit";  
             }
            }  
            else{
               // level=1;
                outputString=outputString+"Invalid Selection"+"\n999:Back\n000.Exit";   
                
            } 
                
            }

        else if (level==3){
            if(key_int<=market.size() && key_int>0){
                mar=market.get(key_int-1).toString();
                
           for(int i=0; i<item.length;i++){
                outputString= outputString + (i+1)+ ":" + item[i]+"\n";
            } 
           outputString=outputString+"999:Back\n000.Exit";
            }
            else{
                outputString=outputString+"Invalid Selection"+"\n999:Back\n000.Exit";
                //level=2;
            }
        }
       else if (level==4){
           String  itm=item[key_int-1].substring(0,1);
            product=mkt.getProduct(loc, mar,itm);
                if(product.isEmpty()){
                    outputString="No Data Found today \n999:Back\n000.Exit";
                }
                 else{
                     Iterator stIterator=product.iterator();
               
                    while(stIterator.hasNext()){
			Marketer_product st=(Marketer_product)stIterator.next();
                       
			String a=st.getProduct_id();
                        String prefix=a.substring(0,1);
                         
                        if("V".equals(prefix))
                        {
			List b=vgt.getProductName(a);
			outputString=outputString+b.get(0) +"- "+st.getUnit_price()+"\n";
                        }
                        else if("F".equals(prefix)){
                         List b=frt.getProductName(a);
			outputString=outputString+b.get(0) +"- "+st.getUnit_price()+"\n"; 
                        }
                       else if("O".equals(prefix)){
                         List b=othr.getProductName(a);
			outputString=outputString+b.get(0) +"- "+st.getUnit_price()+"\n"; 
                        }     
                    }
            }
       } */
        return outputString;
    }
    
    private String sendInfo(final String key){    
         String outputString="dfdf";
       /*  User_detailsDAO ud=new User_detailsDAO();
         
        if(level==1)
        {
            outputString=outputString+"Please Enter your User Name:";     
        }
        else if(level==2){
            outputString=outputString+"Please Enter your Password:";  
            uname = key;
        }
        else if(level==3){
            pw=key;
            System.out.println("pw="+pw);
            System.out.println("uname="+uname);
            List validUser=ud.getUserdetails(uname,pw);
            
            if(!validUser.isEmpty()){
                  Iterator stIterator=validUser.iterator();
                    while(stIterator.hasNext()){
			User_details us=(User_details)stIterator.next();
                       
			 regid=us.getRegistration_id().toString();
                    }
            outputString=outputString+"Please Select your Product Category:\n";
            String category="";
            for(int i=0; i<item.length;i++){
                category=category+(i+1)+ ":" + item[i]+"\n";
            } 
            outputString=outputString+category+"999:Back\n000:Exit";
            }
            else{
                outputString= outputString + "Invalid User! Try again\n999:Back\n000:Exit";  
            }
        }
        else if(level==4){
            int key_int=Integer.parseInt(key);
            category=item[key_int-1];
            outputString=outputString+"Please Enter your Product Name: \n999:Back\n000.Exit";

        }
        else if(level==5){
            outputString=outputString+"Please Enter your Product type description\n999:Back\n000.Exit";
            product_name=key;
           
        }
        else if(level==6){
        outputString=outputString+"Please Select your Product condition:\n";
             product_des=key;
             String con="";
         for(int i=0;i<condition.length;i++)
             con=con+(i+1)+":"+condition[i]+"\n";
          outputString=outputString+con+"999:Back\n000.Exit";
         } 
         else if(level==7){
           int key_int=Integer.parseInt(key);
           outputString=outputString+"Please Enter the amount of product:\n999:Back\n000.Exit";
           product_cond=condition[key_int-1];
         }
         else if(level==8){
            outputString=outputString+"Please Enter the expected price:\n999:Back\n000.Exit";
            amount=key;
         }
         else if(level==9){
            outputString=outputString+"Please Enter the location:\n999:Back\n000.Exit";
            price=key;
         }
         else if(level==10){
             loacation=key;
             
             Farmer_productDAO f=new Farmer_productDAO();
             List<String> pid = null;
             VegetableDAO vd=new VegetableDAO();
             FruitDAO fr=new FruitDAO();
             OtherDAO otr=new OtherDAO();
             
             String sprodid = null;
             if("Vegetable".equals(category)){
                 pid=vd.getProductId(product_name,product_des);  
             }
             else if("Fruit".equals(category)){
                pid=fr.getProductId(product_name,product_des);  
             }
             else if("Other".equals(category)){
               pid =otr.getProductId(product_name,product_des);  
             }
            
            if(!pid.isEmpty()){
             Farmer_product fm=new Farmer_product(regid,pid.get(0).toString(),currentDate,product_cond,amount,price,loacation,loacation_coordinate);
            // Vegetable_details v=new Vegetable_details(regid,product_name,product_des);
            
           
            f.insert_Farmer_product(fm);
           // f.insertVegetable_details(v);
            
            }
            else{
                
             if("Vegetable".equals(category)){
                pro_id=vd.getmaxProductId().get(0).toString();
                int prodid=Integer.parseInt(pro_id.substring(1));
                prodid = prodid+1;
                
                sprodid="V"+prodid ;
                Vegetable_details v=new Vegetable_details(sprodid,product_name,product_des);
                vd.insertVegetable_details(v);
             }
             else if("Fruit".equals(category)){
                pro_id=fr.getmaxProductId().get(0).toString();
                int prodid=Integer.parseInt(pro_id.substring(1));
                prodid = prodid+1;

                sprodid="F"+prodid; 
                Fruit_details f1=new Fruit_details(sprodid,product_name,product_des);
                fr.insertFruit_details(f1);
             }
             else if("Other".equals(category)){
                pro_id=otr.getmaxProductId().get(0).toString();
                int prodid=Integer.parseInt(pro_id.substring(1));
                prodid = prodid+1;

                sprodid ="O"+prodid;
                Other_details o=new Other_details(sprodid,product_name,product_des);
                otr.insertOther_details(o);
             }
             Farmer_product fm=new Farmer_product(regid,sprodid,currentDate,product_cond,amount,price,loacation,loacation_coordinate);
             Vegetable_details v=new Vegetable_details(sprodid,product_name,product_des);
                f.insert_Farmer_product(fm);
               
                
            }
      
        outputString="Your product has been inserted";
         } */
        return outputString;
    }
    
    /**
     * Request sender
     * 
     * @param request
     * @return MtUssdResp
     */
    private MtUssdResp sendRequest(final MtUssdReq request) throws SdpException
    {
        // sending request to service
        MtUssdResp response = null;
        try
        {
            response = ussdMtSender.sendUssdRequest(request);
        }
        catch (SdpException e)
        {
            LOGGER.log(Level.INFO, "Unable to send request", e);
            throw e;
        }

        // response status
        String statusCode = response.getStatusCode();
        String statusDetails = response.getStatusDetail();
        if (StatusCodes.SuccessK.equals(statusCode))
        {
            LOGGER.info("MT USSD message successfully sent");
        }
        else
        {
            LOGGER.info("MT USSD message sending failed with status code [" + statusCode + "] " + statusDetails);
        }
        return response;
    }

    /**
     * Clear history list
     */
    private void clearMenuState()
    {
        LOGGER.info("clear history List");
        menuState.clear();
        //level=0;
    }

    /**
     * Terminate session
     * 
     * @param moUssdReq
     * @throws SdpException
     */
    private void terminateSession(final MoUssdReq moUssdReq) throws SdpException
    {
        final MtUssdReq request = createRequest(moUssdReq, "", USSD_OP_MT_FIN);
        sendRequest(request);
    }

   
    private void backButtonHandle(final MoUssdReq moUssdReq) throws SdpException
    {
        String lastMenuVisited = "0";

        // remove last menu when back
        if (menuState.size() > 0)
        {
            menuState.remove(menuState.size() - 1);
            lastMenuVisited = menuState.get(menuState.size() - 1);
           // level=level-1;   
        }

        // create request and send
        final MtUssdReq request = createRequest(moUssdReq, buildMenuContent(lastMenuVisited), USSD_OP_MT_CONT);
        
        sendRequest(request);

        // clear menu status
        if ("0".equals(lastMenuVisited))
        {
            clearMenuState();
            // add 0 to menu state ,finally its in main menu
            menuState.add((String) "0");
        }

    }

    
    private String getServiceCode(final MoUssdReq moUssdReq)
    {
        //byte serviceCode = 0;
        String serviceCode ="0";
        try
        {
            //serviceCode = Byte.parseByte(moUssdReq.getMessage());
            serviceCode = moUssdReq.getMessage();
        }
        catch (NumberFormatException e)
        {
            return serviceCode;
        }
        // create service codes for child menus based on the main menu codes
        return serviceCode;
    }
  
    private String buildMenuContent(final String selection)
    {
        String menuContent;
        try
        {
            // build menu contents           
            menuContent = getText(selection);
        }
        catch (MissingResourceException e)
        {
            // back to main menu
            menuContent = getText((String) "0");
        }
        return menuContent;
    }
    

}
