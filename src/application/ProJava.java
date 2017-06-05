package application;

import org.jpl7.*;

import javax.swing.JOptionPane;

/**
 * Created by Carlton on 5/6/2017.
 */

public class ProJava {

    public ProJava(){   // class default constructor
        consultProlog();	// call consultProlog method
    }

    private void consultProlog() { // method used to consult prolog knowledge base file

        String file = (System.getProperty("user.dir").toString() + "\\Diacheck.pl");
        Query q1 = new Query("consult", new Term[] {new Atom(file)});
        String mess = "Consult" + (q1.hasSolution() ? " Successful" : " Failed");
        JOptionPane.showMessageDialog(null, mess);
    }

    public static String Ethnic(String race){ // method used to call predicate responsible for assertion of new risk ethnicity

        Atom pro_race;
        pro_race = new Atom(race);
        Variable X = new Variable("X");
        String str_race = "";
        Object ethnic_obj = null;
        Term goal_ethnic = new Compound("ethn", new Term[]{pro_race, X});
        Query q_ethnic = new Query(goal_ethnic);
        while(q_ethnic.hasMoreElements()){
            ethnic_obj = q_ethnic.nextElement();
            str_race = ethnic_obj.toString();
            str_race = cleanData(str_race);
        }
        return str_race;

    }

    // method used to strip results from prolog of unwanted characters
    private static String cleanData(String data)
    {
        data = data.replace("X", "");
        data = data.replace("Y=", "");
        data = data.replace("Z", "");
        data = data.replace("=", "");
        data = data.replace("'[|]'", "");
        data = data.replace("('", "\n");
        data = data.replace("','","");
        data = data.replace("{"," ");
        data = data.replace("(", "");
        data = data.replace("'", "");
        data = data.replace(")", "");
        data = data.replace(",","");
        data = data.replace("[]","");
        data = data.replace("}", "");
        return data;
    }

    // Method used to capture input from survey for male participants and allocate to respective predicates in prolog knowledge base for processing
    public static String javaToProlog(String age, String gender, String ethnic,
                                      String weght, String feet,String inches, String actv,
                                      String fam_history,String prediab){
        org.jpl7.Integer pro_age=  new org.jpl7.Integer(java.lang.Integer.parseInt(age));
        org.jpl7.Integer pro_weight= new org.jpl7.Integer(java.lang.Integer.parseInt(weght));
        org.jpl7.Integer pro_feet = new org.jpl7.Integer(java.lang.Integer.parseInt(feet));
        org.jpl7.Integer pro_inches = new org.jpl7.Integer(java.lang.Integer.parseInt(inches));
        org.jpl7.Integer pro_actv = new org.jpl7.Integer(java.lang.Integer.parseInt(actv));
        org.jpl7.Integer pro_famhis = new org.jpl7.Integer(java.lang.Integer.parseInt(fam_history));
        org.jpl7.Integer pro_prediab = new org.jpl7.Integer(java.lang.Integer.parseInt(prediab));
        org.jpl7.Integer pro_gest = new org.jpl7.Integer(java.lang.Integer.parseInt("2"));
        org.jpl7.Integer pro_poly = new org.jpl7.Integer(java.lang.Integer.parseInt("2"));

        int weight_int, fee_int, inch_int;
        int bmi_int;
        weight_int = java.lang.Integer.parseInt(weght);
        fee_int = java.lang.Integer.parseInt(feet);
        inch_int = java.lang.Integer.parseInt(inches);
        bmi_int = (int) ((int)(weight_int * 0.454)/(Math.pow((fee_int*0.305)+(inch_int*0.0254), 2)));
        String bmi_str = java.lang.Integer.toString(bmi_int);

        Atom pro_ethnic;


        pro_ethnic = new Atom(ethnic);
        Variable X = new Variable("X");
        Variable Y = new Variable("Y");
        Variable Z = new Variable("Z");
        Variable bmi = new Variable("bmi");
        Variable rate = new Variable("rate");
        Object adv = null;
        Object rate_obj=null;
        Object disp = null;
        Object emerg = null;
        Object track = null;

        // call to predicate in prolog knowledge base responsible for calculating BMI
        Term goal_bmi = new Compound("calcBmi1", new Term[]{pro_weight,pro_feet,pro_inches, bmi});
        Query q_bmi = new Query(goal_bmi);
        while(q_bmi.hasMoreElements()){
            q_bmi.nextElement();
        }

        org.jpl7.Float pro_bmi = new org.jpl7.Float(java.lang.Float.parseFloat(bmi_str));

        // call to predicate in prolog knowledge base responsible for determining the risk level of participant
        Term goal_risk = new Compound("risk1", new Term[]{pro_age,pro_ethnic,pro_bmi,pro_actv,pro_famhis,pro_prediab,pro_gest,pro_poly,rate});
        Query q_risk = new Query(goal_risk);
        while(q_risk.hasMoreElements()){
            rate_obj = q_risk.nextElement();
        }

        String test= rate_obj.toString();
        test = cleanData(test);
        test=test.replace("rate", "");
        test = test.replace(" ", "");

        org.jpl7.Integer pro_rate = new org.jpl7.Integer(java.lang.Integer.parseInt(test));


        String str_disp= "";
        Term goal_disp= new Compound("disp1", new Term[]{pro_rate, X}); // call to predicate in prolog knowledge base responsible for displaying risk level of participant
        Query q_disp = new Query(goal_disp);
        while(q_disp.hasMoreElements()){
            disp = q_disp.nextElement();
            str_disp = disp.toString();
            str_disp = cleanData(str_disp)+ pro_rate.toString();
        }

        String str_emerg = "";
        Term goal_emerg = new Compound("emergency1", new Term[]{pro_rate,X}); // call to predicate in prolog knowledge base responsible for suggestion to high risk participants
        Query q_emerg = new Query(goal_emerg);
        while(q_emerg.hasMoreElements()){
            emerg = q_emerg.nextElement();
            str_emerg = emerg.toString();
            str_emerg = cleanData(str_emerg);
        }
        String str_track= "";
        String str_builder= "";
        String sb[]= new String[]{};
        Term goal_track = new Compound("track",new Term[]{pro_rate,X,Y,Z}); //call to predicate in prolog responsible for keep track of participants who took part in survey
        Query q_track = new Query(goal_track);
        while(q_track.hasMoreElements()){
            track = q_track.nextElement();
            str_track = track.toString();
            str_track = cleanData(str_track);
            sb = str_track.split("\\s+");
            str_builder="Total Participants: "+ sb[1] + "\nHigh Risk Participants: " + sb[2] +"\n"+ sb[3]+ "% of participants are at a high risk of getting type 2 diabetes";
            str_track = str_builder;
        }

        String str_advice = "";
        Term goal_advice = new Compound("adv1", new Term[]{pro_bmi, X}); // call to predicate in prolog knowledge base responsible for providing expert advice according to risk level of participant
        Query q_advice = new Query(goal_advice);
        while(q_advice.hasMoreElements()){
            adv = q_advice.nextElement();
            str_advice = adv.toString();
            str_advice = "\n********Advice From Experts******"+cleanData(str_advice);
        }
        return str_disp+str_advice+"\n"+str_emerg+"\n"+"******Participants Recorded*******\n"+str_track;

    }

    // Method used to capture input from survey for female participants and allocate to respective predicates in prolog knowledge base for processing
    public static String javaToProlog(String age, String gender, String ethnic,
                                      String weght, String feet,String inches, String actv,
                                      String fam_history,String prediab, String gest, String poly){

        org.jpl7.Integer pro_age=  new org.jpl7.Integer(java.lang.Integer.parseInt(age));
        org.jpl7.Float pro_weight= new org.jpl7.Float(java.lang.Float.parseFloat(weght));
        org.jpl7.Float pro_feet = new org.jpl7.Float(java.lang.Float.parseFloat(feet));
        org.jpl7.Float pro_inches = new org.jpl7.Float(java.lang.Float.parseFloat(inches));
        org.jpl7.Integer pro_actv = new org.jpl7.Integer(java.lang.Integer.parseInt(actv));
        org.jpl7.Integer pro_famhis = new org.jpl7.Integer(java.lang.Integer.parseInt(fam_history));
        org.jpl7.Integer pro_prediab = new org.jpl7.Integer(java.lang.Integer.parseInt(prediab));
        org.jpl7.Integer pro_gest = new org.jpl7.Integer(java.lang.Integer.parseInt(gest));
        org.jpl7.Integer pro_poly = new org.jpl7.Integer(java.lang.Integer.parseInt(poly));

        int weight_int, fee_int, inch_int;
        int bmi_int;

        weight_int = java.lang.Integer.parseInt(weght);
        fee_int = java.lang.Integer.parseInt(feet);
        inch_int = java.lang.Integer.parseInt(inches);
        bmi_int = (int) ((int)(weight_int * 0.454)/(Math.pow((fee_int*0.305)+(inch_int*0.0254), 2)));
        String bmi_str = java.lang.Integer.toString(bmi_int);

        Atom pro_ethnic;
        new Atom(gender);
        pro_ethnic = new Atom(ethnic);
        Variable X = new Variable("X");
        Variable Y = new Variable("Y");
        Variable Z = new Variable("Z");
        Variable bmi = new Variable("bmi");
        Variable rate = new Variable("rate");
        Object adv = null;
        Object rate_obj=null;
        Object disp = null;
        Object emerg = null;
        Object track = null;

        // call to predicate in prolog knowledge base responsible for calculating BMI
        Term goal_bmi = new Compound("calcBmi1", new Term[]{pro_weight,pro_feet,pro_inches, bmi});
        Query q_bmi = new Query(goal_bmi);
        while(q_bmi.hasMoreElements()){
            q_bmi.nextElement();
        }

        org.jpl7.Float pro_bmi = new org.jpl7.Float(java.lang.Float.parseFloat(bmi_str));

        // call to predicate in prolog knowledge base responsible for determining the risk level of participant
        Term goal_risk = new Compound("risk1", new Term[]{pro_age,pro_ethnic,pro_bmi,pro_actv,pro_famhis,pro_prediab,pro_gest,pro_poly,rate});
        Query q_risk = new Query(goal_risk);
        while(q_risk.hasMoreElements()){
            rate_obj = q_risk.nextElement();
        }
        String test= null;
        if (rate_obj != null) {
            test = rate_obj.toString();
        }
        test = cleanData(test);
        test=test.replace("rate", "");
        test = test.replace(" ", "");

        org.jpl7.Integer pro_rate = new org.jpl7.Integer(java.lang.Integer.parseInt(test));

        String str_disp= "";
        Term goal_disp= new Compound("disp1", new Term[]{pro_rate, X}); // call to predicate in prolog knowledge base responsible for displaying risk level of participant
        Query q_disp = new Query(goal_disp);
        while(q_disp.hasMoreElements()){
            disp = q_disp.nextElement();
            str_disp = disp.toString();
            str_disp = cleanData(str_disp)+ pro_rate.toString();
        }

        String str_emerg = "";
        Term goal_emerg = new Compound("emergency1", new Term[]{pro_rate,X}); // call to predicate in prolog knowledge base responsible for suggestion to high risk participants
        Query q_emerg = new Query(goal_emerg);
        while(q_emerg.hasMoreElements()){
            emerg = q_emerg.nextElement();
            str_emerg = emerg.toString();
            str_emerg = cleanData(str_emerg);
        }

        String str_track= "";
        String str_builder= "";
        String sb[]= new String[]{};
        Term goal_track = new Compound("track",new Term[]{pro_rate,X,Y,Z}); //call to predicate in prolog responsible for keep track of participants who took part in survey
        Query q_track = new Query(goal_track);
        while(q_track.hasMoreElements()){
            track = q_track.nextElement();
            str_track = track.toString();
            str_track = cleanData(str_track);
            sb = str_track.split("\\s+");
            str_builder="Total Participants: "+ sb[1] + "\nHigh Risk Participants: " + sb[2] +"\n"+ sb[3]+ "% of participants are at a high risk of getting type 2 diabetes";
            str_track = str_builder;
        }

        String str_advice = "";
        Term goal_advice = new Compound("adv1", new Term[]{pro_bmi, X}); // call to predicate in prolog knowledge base responsible for providing expert advice according to risk level of participant
        Query q_advice = new Query(goal_advice);
        while(q_advice.hasMoreElements()){
            adv = q_advice.nextElement();
            str_advice = adv.toString();
            str_advice = "\n********Advice From Experts******"+cleanData(str_advice);
        }
        return str_disp+str_advice+"\n"+str_emerg+"\n"+"******Participants Recorded*******\n"+str_track;
    }



}



