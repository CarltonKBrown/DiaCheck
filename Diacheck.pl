
%Authors: John Blake, Carlton Brown, Omar McNeil, Keval King
%
% ***********************Dia-Check***************************************
% The expert system was designed in consideration of the requirements of
% the Ministry of Health. The system has been: Be populated with details
% the risk factors that may determine the possibility of getting type 2
% diabetes. Accept user data (age, ethnicity and information to
% calculate BMI). Store all data entered by the user Make predictions
% based on details provided and give advice to users who are at risk%


:- dynamic riskethnic/1.
:- dynamic counts/2.


%Ethnicty list%
elist1(ethnicity,[black,white,indian,chinese,syrian,lebanese]).
%Risk ethnicity%
riskethnic(black).
%Useful predicates and facts.%
counts(0,0).
riskfact(Age):- Age >= 45.
riskfact(Bmi):-Bmi >29.
long_term1('Start Eating Healthy and Balanced Meals').
long_term1('Maintain Good Physical and Mental Health - Weight Loss is Strongly Advised').
long_term1('No more Burger King and KFC').
short_term1('Reduce Alcohol Consumption').
short_term1('Quit Smoking').
urgent1('See a Doctor Urgently!!').
low_risk('You have a low risk of getting type 2 diabetes, Risk Level: ').
med_risk('You are at risk of getting type 2 diabetes, Risk Level: ').
high_risk('You have a very high risk of getting type 2 diabetes, Risk Level: ').
ethnic_recorded('Ethnicity already recorded**').
ethnic_invalid('Invalid Ethnicity').
ethnic_assert('Ethinicity assertion successful').

% Keeps track of participants recorded%
track(Rating,Runtimes,Select,A):-  counts(X,Y),Runtimes is X + 1,((Rating>7-> Select is Y + 1);Select is Y),retractall(counts(_,_)),assert(counts(Runtimes,Select)),A is ((Select/Runtimes)*100).

%Assert new at risk ethnicities to risk list%%%
ethn(Ethc,X):-elist1(ethnicity,Elist1),(not(member(Ethc,Elist1))->ethnic_invalid(X),ethn(X),!;riskethnic(Ethc) ->ethnic_recorded(X);assert(riskethnic(Ethc)),ethnic_assert(X)).

%Calculates BMI%
calcBmi1(Weight,Feet,Inches,Bmi):-X is (Weight *0.454),Y is (Feet *0.305),Z is (Inches * 0.0254),Bmi is X/((Y+Z)^2).

%Determines risk level%
risk1(Age,Ethnic,Bmi,Opt,Opt2,Opt3,Opt4,Opt5,Rating):-(riskfact(Age)->A is 1;A is 0),(Ethnic==black->B is 1;B is 0),(riskfact(Bmi)->C is 5;C is 0),(Opt==2-> D is 1;D is 0),(Opt2==1->E is 1;E is 0),(Opt3==1->F is 1;F is 0),(Opt4==1->G is 1;G is 0),(Opt5==1->H is 1;H is 0),Rating is A+B+C+D+E+F+G+H.

%displays risk level%
disp1(Rating,Risk):-nl,Rating=<4->nl,low_risk(Risk);Rating>=5,Rating=<7->nl,med_risk(Risk);Rating>7,nl,high_risk(Risk).

%Determines advice for participant%
adv1(Bmi,X):-nl,riskfact(Bmi)->nl,findall(Advice,long_term1(Advice),X);nl,findall(Advice,short_term1(Advice),X).

%Displays message if participant's risk level is high*
emergency1(Rating,Advice):-Rating>7->nl,urgent1(Advice).
