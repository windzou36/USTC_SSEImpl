
/**
 *  ��axis2�����Զ����ɵ����ڱ��ز������ű���Ĵ���
 * GetNewsTitle_ustcCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

    package ustcNews;

    /**
     *  GetNewsTitle_ustcCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GetNewsTitle_ustcCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GetNewsTitle_ustcCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GetNewsTitle_ustcCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for GetNewsTitleList method
            * override this method for handling normal response from GetNewsTitleList operation
            */
           public void receiveResultGetNewsTitleList(
                    ustcNews.GetNewsTitle_ustcStub.GetNewsTitleListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetNewsTitleList operation
           */
            public void receiveErrorGetNewsTitleList(java.lang.Exception e) {
            }
                


    }
    