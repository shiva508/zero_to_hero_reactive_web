package com.pool.model;

public class Permissions {

        private boolean admin;
        private boolean maintain;
        private boolean push;
        private boolean triage;
        private boolean pull;


        // Getter Methods

        public boolean getAdmin() {
        return admin;
    }

        public boolean getMaintain() {
        return maintain;
    }

        public boolean getPush() {
        return push;
    }

        public boolean getTriage() {
        return triage;
    }

        public boolean getPull() {
        return pull;
    }

        // Setter Methods

        public void setAdmin( boolean admin ) {
        this.admin = admin;
    }

        public void setMaintain( boolean maintain ) {
        this.maintain = maintain;
    }

        public void setPush( boolean push ) {
        this.push = push;
    }

        public void setTriage( boolean triage ) {
        this.triage = triage;
    }

        public void setPull( boolean pull ) {
        this.pull = pull;
    }
}
