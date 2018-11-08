--this file contain the objects

--cinq personnes etudiants
!create rob:Etudiant
!set rob.name:= 'Rob'
!set rob.email:= 'Rob@email.com'
!set rob.studentNumber:=00001
!set rob.CGPA:=7.0

!create eli:Etudiant
!set eli.name:='Eli'
!set eli.email:='Eli@email.com'
!set eli.studentNumber:=00002
!set eli.CGPA:=9.0

!create bob:Etudiant
!set bob.name:='Bob'
!set bob.email:='Bob@email.com'
!set bob.studentNumber:=00003
!set bob.CGPA:=8.0

!create emy:Etudiant
!set emy.name:='Emy'
!set emy.email:='Emy@email.com'
!set emy.studentNumber:=00004
!set emy.CGPA:=6.0

!create tom:Etudiant
!set tom.name:='Tom'
!set tom.email:='Tom@email.com'
!set tom.studentNumber:=00005
!set tom.CGPA:=7.5
--------------------------------------------



--six bourses 
!create bourseFrancais:Bourses
!set bourseFrancais.type:='francais'
!set bourseFrancais.groupe:='francais'
!set bourseFrancais.name:='Bourse dimmersion francaise'
!set bourseFrancais.valeur:=1000
!set bourseFrancais.yearOfStudy:='2017'

!create bourse_80:Bourses
!set bourse_80.type:='admission'
!set bourse_80.groupe:='note 80'
!set bourse_80.name:='Bourse dadmission de 80'
!set bourse_80.valeur:=1000
!set bourse_80.yearOfStudy:='2017'

!create bourse_90:Bourses
!set bourse_90.type:='admission'
!set bourse_90.groupe:='note 90'
!set bourse_90.name:='Bourse dadmission de 90'
!set bourse_90.valeur:=3000
!set bourse_90.yearOfStudy:='2017'

!create bourseOne:Bourses
!set bourseOne.type:='reguliere'
!set bourseOne.groupe:='Exclusive'
!set bourseOne.name:='Bourse demployeur'
!set bourseOne.valeur:=500
!set bourseOne.yearOfStudy:='2017'

!create bourseTwo:Bourses
!set bourseTwo.type:='reguliere'
!set bourseTwo.groupe:='Exclusive'
!set bourseTwo.name:='Bourse demployeur'
!set bourseTwo.valeur:=2000
!set bourseTwo.yearOfStudy:='2017'

!create bourseThree:Bourses
!set bourseThree.type:='reguliere'
!set bourseThree.groupe:='Special'
!set bourseThree.name:='Bourse dinnovation'
!set bourseThree.valeur:=5000
!set bourseThree.yearOfStudy:='2017'
----------------------------------------


--cinq application
!create application1:Application
!set application1.presentDayDate:= 199

!create application2:Application
!set application2.presentDayDate:= 167

!create application3:Application
!set application3.presentDayDate:= 159

!create application4:Application
!set application4.presentDayDate:= 199

!create application5:Application
!set application5.presentDayDate:= 55
-------------------------------------------

--Lien
!insert(rob, application1) into demande
!insert(eli, application2) into demande
!insert(bob, application3) into demande
!insert(emy, application4) into demande
!insert(tom, application5) into demande

!insert(bourseFrancais,application1) into obtient
!insert(bourse_90, application2) into obtient
!insert(bourse_80, application3) into obtient
!insert(bourseThree, application3) into obtient
!insert(bourseOne, application4) into obtient
!insert(bourseTwo, application5) into obtient

--------------------------------------
--------------------------------------

--creation d'un recommandateur et d'une lettre
!create maurice:Recommandateur
!set maurice.name:='Maurice'

!create miguel:Recommandateur
!set miguel.name:='Miguel'

!create herve:Recommandateur
!set herve.name:='Herve'

!create lettre1:LettresDeRecommandation
!set lettre1.startDayDate:= 156
!set lettre1.scores:= 4.9

!create lettre2:LettresDeRecommandation
!set lettre2.startDayDate:= 180
!set lettre2.scores:=3.0

!create lettre3:LettresDeRecommandation
!set lettre3.startDayDate:= 1
!set lettre3.scores:=5.0

-----------------------------------------
!insert (maurice, lettre1) into ecrit
!insert (miguel, lettre2) into ecrit
!insert (herve, lettre3) into ecrit

!insert (application3,lettre1) into a
!insert (application4, lettre2) into a
!insert (application5, lettre3) into a

