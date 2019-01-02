package com.bril.keypersonsupervision.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SelectSupervisorsInfoBean implements Parcelable {

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

    public static class HeguardianBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.created_time);
            dest.writeString(this.updated_time);
            dest.writeString(this.type_name);
            dest.writeString(this.contact);
            dest.writeString(this.name);
            dest.writeString(this.level);
            dest.writeInt(this.id);
            dest.writeString(this.type);
            dest.writeString(this.relationship);
            dest.writeInt(this.patients_id);
        }

        public HeguardianBean() {
        }

        protected HeguardianBean(Parcel in) {
            this.created_time = in.readString();
            this.updated_time = in.readString();
            this.type_name = in.readString();
            this.contact = in.readString();
            this.name = in.readString();
            this.level = in.readString();
            this.id = in.readInt();
            this.type = in.readString();
            this.relationship = in.readString();
            this.patients_id = in.readInt();
        }

        public static final Creator<HeguardianBean> CREATOR = new Creator<HeguardianBean>() {
            @Override
            public HeguardianBean createFromParcel(Parcel source) {
                return new HeguardianBean(source);
            }

            @Override
            public HeguardianBean[] newArray(int size) {
                return new HeguardianBean[size];
            }
        };
    }

    public static class ComprehensiveBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.created_time);
            dest.writeString(this.updated_time);
            dest.writeString(this.type_name);
            dest.writeString(this.contact);
            dest.writeString(this.level);
            dest.writeString(this.name);
            dest.writeInt(this.id);
            dest.writeString(this.type);
            dest.writeString(this.relationship);
            dest.writeInt(this.patients_id);
        }

        public ComprehensiveBean() {
        }

        protected ComprehensiveBean(Parcel in) {
            this.created_time = in.readString();
            this.updated_time = in.readString();
            this.type_name = in.readString();
            this.contact = in.readString();
            this.level = in.readString();
            this.name = in.readString();
            this.id = in.readInt();
            this.type = in.readString();
            this.relationship = in.readString();
            this.patients_id = in.readInt();
        }

        public static final Creator<ComprehensiveBean> CREATOR = new Creator<ComprehensiveBean>() {
            @Override
            public ComprehensiveBean createFromParcel(Parcel source) {
                return new ComprehensiveBean(source);
            }

            @Override
            public ComprehensiveBean[] newArray(int size) {
                return new ComprehensiveBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.heguardian, flags);
        dest.writeParcelable(this.comprehensive, flags);
    }

    public SelectSupervisorsInfoBean() {
    }

    protected SelectSupervisorsInfoBean(Parcel in) {
        this.heguardian = in.readParcelable(HeguardianBean.class.getClassLoader());
        this.comprehensive = in.readParcelable(ComprehensiveBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SelectSupervisorsInfoBean> CREATOR = new Parcelable.Creator<SelectSupervisorsInfoBean>() {
        @Override
        public SelectSupervisorsInfoBean createFromParcel(Parcel source) {
            return new SelectSupervisorsInfoBean(source);
        }

        @Override
        public SelectSupervisorsInfoBean[] newArray(int size) {
            return new SelectSupervisorsInfoBean[size];
        }
    };
}
