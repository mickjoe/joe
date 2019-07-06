package com.spark.biben.custome.entity;

import java.util.List;

public class Areas extends BaseData{

    /**
     * code : 0
     * message : success
     * data : [{"zhName":"中国","areaCode":"86","enName":"China","language":"zh_CN","localCurrency":"CNY","sort":"1","status":"0"},{"zhName":"菲律宾","areaCode":"63","enName":"Filipino","language":"tl_PH","localCurrency":"PHP","sort":"2","status":"0"},{"zhName":"柬埔寨","areaCode":"855","enName":"Khmer","language":"km_KH","localCurrency":"KHR","sort":"3","status":"0"},{"zhName":"泰国","areaCode":"66","enName":"Thailand","language":"th_TH","localCurrency":"THB","sort":"4","status":"0"},{"zhName":"马来西亚","areaCode":"60","enName":"Malay","language":"ms_MY","localCurrency":"MYR","sort":"5","status":"0"},{"zhName":"新加坡","areaCode":"65","enName":"Singapore","language":"en_US","localCurrency":"SGD","sort":"6","status":"0"},{"zhName":"中国台湾","areaCode":"886","enName":"Taiwan,China","language":"zh_TW","localCurrency":"TWD","sort":"8","status":"0"},{"zhName":"中国香港","areaCode":"852","enName":"HongKong","language":"zh_HK","localCurrency":"HKD","sort":"9","status":"0"},{"zhName":"中国澳门","areaCode":"853","enName":"Macao","language":"zh_Mo","localCurrency":"AUD","sort":"10","status":"0"},{"zhName":"缅甸","areaCode":"95","enName":"Burmese","language":"my_MM","localCurrency":"BUK","sort":"11","status":"0"},{"zhName":"越南","areaCode":"84","enName":"Vietnam","language":"vi_VN","localCurrency":"VND","sort":"12","status":"0"},{"zhName":"印度尼西亚","areaCode":"62","enName":"Indonesian","language":"in_ID","localCurrency":"IDR","sort":"13","status":"0"},{"zhName":"日本","areaCode":"81","enName":"Japan","language":"ja_JP","localCurrency":"JPY","sort":"14","status":"0"},{"zhName":"韩国","areaCode":"82","enName":"Korea","language":null,"localCurrency":"KRW","sort":"15","status":"0"},{"zhName":"美国","areaCode":"1","enName":"America","language":"en_US","localCurrency":"USD","sort":"19","status":"0"}]
     */

    private List<DataBean> data;


    public List<DataBean> getData(){
        return data;
    }

    public void setData(List<DataBean> data){
        this.data = data;
    }

    public static class DataBean{
        /**
         * zhName : 中国
         * areaCode : 86
         * enName : China
         * language : zh_CN
         * localCurrency : CNY
         * sort : 1
         * status : 0
         */

        private String zhName;
        private String areaCode;
        private String enName;
        private String language;
        private String localCurrency;
        private String sort;
        private String status;

        public String getZhName(){
            return zhName;
        }

        public void setZhName(String zhName){
            this.zhName = zhName;
        }

        public String getAreaCode(){
            return areaCode;
        }

        public void setAreaCode(String areaCode){
            this.areaCode = areaCode;
        }

        public String getEnName(){
            return enName;
        }

        public void setEnName(String enName){
            this.enName = enName;
        }

        public String getLanguage(){
            return language;
        }

        public void setLanguage(String language){
            this.language = language;
        }

        public String getLocalCurrency(){
            return localCurrency;
        }

        public void setLocalCurrency(String localCurrency){
            this.localCurrency = localCurrency;
        }

        public String getSort(){
            return sort;
        }

        public void setSort(String sort){
            this.sort = sort;
        }

        public String getStatus(){
            return status;
        }

        public void setStatus(String status){
            this.status = status;
        }
    }
}
