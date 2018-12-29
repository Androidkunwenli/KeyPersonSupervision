package com.bril.keypersonsupervision.bean;

public class SelectSupervisorsInfoBean {

    /**
     * heguardian : {"created_time":"2018-12-21T07:12:15.000+0000","updated_time":"2018-12-21T07:12:15.000+0000","type_name":"监护人","contact":"18665578895","name":"李四","id":1,"type":"4","relationship":"父亲","patients_id":2}
     * comprehensive : {"created_time":"2018-12-21T07:13:40.000+0000","updated_time":"2018-12-21T07:13:40.000+0000","type_name":"卫生部门","contact":"1855669854","name":"王五","id":2,"type":"2","relationship":"关系与患者关系","patients_id":2}
     */

    private HeguardianBean heguardian;
    private ComprehensiveBean comprehensive;

    public HeguardianBean getHeguardian() {
        return heguardian;
    }

    public void setHeguardian(HeguardianBean heguardian) {
        this.heguardian = heguardian;
    }

    public ComprehensiveBean getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(ComprehensiveBean comprehensive) {
        this.comprehensive = comprehensive;
    }

    public static class HeguardianBean {
        /**
         * created_time : 2018-12-21T07:12:15.000+0000
         * updated_time : 2018-12-21T07:12:15.000+0000
         * type_name : 监护人
         * contact : 18665578895
         * name : 李四
         * id : 1
         * type : 4
         * relationship : 父亲
         * patients_id : 2
         */

        private String created_time;
        private String updated_time;
        private String type_name;
        private String contact;
        private String name;
        private String level;
        private int id;
        private String type;
        private String relationship;
        private int patients_id;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getUpdated_time() {
            return updated_time;
        }

        public void setUpdated_time(String updated_time) {
            this.updated_time = updated_time;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public int getPatients_id() {
            return patients_id;
        }

        public void setPatients_id(int patients_id) {
            this.patients_id = patients_id;
        }
    }

    public static class ComprehensiveBean {
        /**
         * created_time : 2018-12-21T07:13:40.000+0000
         * updated_time : 2018-12-21T07:13:40.000+0000
         * type_name : 卫生部门
         * contact : 1855669854
         * name : 王五
         * id : 2
         * type : 2
         * relationship : 关系与患者关系
         * patients_id : 2
         */

        private String created_time;
        private String updated_time;
        private String type_name;
        private String contact;
        private String level;
        private String name;
        private int id;
        private String type;
        private String relationship;
        private int patients_id;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getUpdated_time() {
            return updated_time;
        }

        public void setUpdated_time(String updated_time) {
            this.updated_time = updated_time;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public int getPatients_id() {
            return patients_id;
        }

        public void setPatients_id(int patients_id) {
            this.patients_id = patients_id;
        }
    }
}
