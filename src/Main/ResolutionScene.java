package Main;

import java.io.IOException;

public class ResolutionScene {

private Boolean BtnRes = true;
    public void btnResClick() throws IOException {
        if(this.BtnRes) {
            BtnRes = false;
        }
        else {
            BtnRes = true;
        }

        }




    public  Boolean getRes(){
        return BtnRes;
    }


}
//
