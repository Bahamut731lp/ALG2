\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{tulthesis}[2021/01/18 TUL thesis class version 1.10]
% Licence: CC BY

\newcommand{\TUL@baseclass}{report}
\DeclareOption{article}{\renewcommand{\TUL@baseclass}{article}}
\DeclareOption{report}{}

\newcommand{\TUL@levelCZ}{Bakal\'{a}\v{r}sk\'{a} pr\'{a}ce}
\newcommand{\TUL@levelEN}{Bachelor thesis}
\newcommand{\TUL@kat}{BP}

\DeclareOption{EN}{\PassOptionsToPackage{\CurrentOption}{EN}}
\DeclareOption{BP}{}
\DeclareOption{DP}{
    \renewcommand{\TUL@levelCZ}{Diplomov\'{a} pr\'{a}ce}
    \renewcommand{\TUL@levelEN}{Master thesis}
    \renewcommand{\TUL@kat}{DP}}
\DeclareOption{Dis}{
    \renewcommand{\TUL@levelCZ}{Diserta\v cn\'\i\ pr\'{a}ce}
    \renewcommand{\TUL@levelEN}{Dissertation}
    \renewcommand{\TUL@kat}{Dis}}
\DeclareOption{Hab}{
    \renewcommand{\TUL@levelCZ}{Habilita\v cn\'\i\ pr\'{a}ce}
    \renewcommand{\TUL@levelEN}{Habilitation thesis}
    \renewcommand{\TUL@kat}{Hab}}
\DeclareOption{Teze}{
    \renewcommand{\TUL@levelCZ}{Teze diserta\v cn\'\i\ pr\'{a}ce}
    \renewcommand{\TUL@levelEN}{Dissertation thesis draft}
    \renewcommand{\TUL@kat}{Teze}}
\DeclareOption{Autoref}{
    \renewcommand{\TUL@levelCZ}{Autorefer\'{a}t diserta\v cn\'\i\ pr\'{a}ce}
    \renewcommand{\TUL@levelEN}{Dissertation thesis summary}
    \renewcommand{\TUL@kat}{Autoref}}
\DeclareOption{RP}{
    \renewcommand{\TUL@levelCZ}{Ro\v cn\'\i kov\'y projekt}
    \renewcommand{\TUL@levelEN}{Project report}
    \renewcommand{\TUL@kat}{RP}}
\DeclareOption{MP}{
    \renewcommand{\TUL@levelCZ}{Magistersk\'y projekt}
    \renewcommand{\TUL@levelEN}{Master project report}
    \renewcommand{\TUL@kat}{MP}}
\DeclareOption{FS}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{FT}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{FP}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{EF}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{FA}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{FM}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{FZS}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{UZS}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{CXI}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{bwtitles}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{bw}{\PassOptionsToPackage{\CurrentOption}{tul}}
\DeclareOption{fonts}{\PassOptionsToPackage{\CurrentOption}{tul}}
\newif\ifTUL@pdf
\TUL@pdftrue
\DeclareOption{nopdf}{\TUL@pdffalse}
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{\TUL@baseclass}}
\ProcessOptions\relax

\LoadClass[a4paper,12pt]{\TUL@baseclass}

\RequirePackage[noheader]{tul}
\noTULfooter

\RequirePackage{ifthen}
\RequirePackage{tabularx}
\RequirePackage{pdfpages}

\ifTUL@pdf
 \RequirePackage{hyperref}
 \hypersetup{colorlinks=true, linkcolor=tul, urlcolor=tul, citecolor=tul}
\fi

\newcommand{\TULthesisType}[2]{\renewcommand{\TUL@levelCZ}{#1}%
\renewcommand{\TUL@levelEN}{#2}}

\newcommand{\TUL@nazevCZ}{Chyb\'\i\ \textbackslash TULtitle\{\v{c}esky\}\{anglicky\}}
\newcommand{\TUL@nazevEN}{English title}
\newcommand{\TULtitle}[2]{\renewcommand{\TUL@nazevCZ}{#1}%
\renewcommand{\TUL@nazevEN}{#2}%
\ifTUL@pdf
 \ifthenelse{\equal{\TUL@nguage}{EN}}{\hypersetup{pdftitle={#2}}}{\hypersetup{pdftitle={#1}}}
\fi}

\newcommand{\TUL@autor}{Chyb\'\i\ \textbackslash TULauthor\{jm\'eno\}}
\newcommand{\TULauthor}[1]{\renewcommand{\TUL@autor}{#1}
\ifTUL@pdf
 \hypersetup{pdfauthor={#1}}
\fi}

\newcommand{\TUL@vedouci}{Chyb\'\i\ \textbackslash TULsupervisor\{jm\'eno\}}
\newcommand{\TULsupervisor}[1]{\renewcommand{\TUL@vedouci}{#1}}

\newcounter{TULk@nzult}
\setcounter{TULk@nzult}{0}
%\newcommand{\TULconsultant}[1]{\newcommand{\TUL@konzultant}{#1}}
\newcommand{\TULconsultant}[1]{\stepcounter{TULk@nzult}\@namedef{\roman{TULk@nzult}@TULkonzultant}{#1}}

\newcommand{\TUL@programCZ}{Chyb\'\i\ \textbackslash TULprogramme\{k\'od\}\{\v{c}esky\}\{anglicky\}}
\newcommand{\TUL@programEN}{Study Programme}
\newcommand{\TULprogramme}[3]{\renewcommand{\TUL@programCZ}{#1 -- #2}%
\renewcommand{\TUL@programEN}{#1 -- #3}}

\newcounter{TUL@bor}
\setcounter{TUL@bor}{0}
\newcounter{TULc@unter}
\newcommand{\i@TULoborCZ}{Chyb\'\i\ \textbackslash TULbranch\{k\'od\}\{\v{c}esky\}\{anglicky\}}
\newcommand{\i@TULoborEN}{Study Branches}
\newcommand{\TULbranch}[3]{%
  \stepcounter{TUL@bor}
  \ifthenelse{\equal{#1}{}}{
    \@namedef{\roman{TUL@bor}@TULoborCZ}{#2}%
    \@namedef{\roman{TUL@bor}@TULoborEN}{#3}%
  }{
    \@namedef{\roman{TUL@bor}@TULoborCZ}{#1 -- #2}%
    \@namedef{\roman{TUL@bor}@TULoborEN}{#1 -- #3}%
  }
}

\newcommand{\TUL@rok}{\the\year}
\newcommand{\TULyear}[1]{\renewcommand{\TUL@rok}{#1}}

\newcommand{\TULid}[1]{\newcommand{\TUL@id}{#1}}

% podpurne prikazy
\newcommand{\@ddel}{\hspace{0.5em}\rule[-0.2em]{0.75pt}{1.2em}\hspace{0.5em}}

\newenvironment{uzky@text}{\begin{center}\begin{minipage}{0.80\textwidth}
\setlength{\parskip}{0.5\baselineskip}}{\end{minipage}\end{center}}


% rozmery stranky a styl zapati
\setlength{\oddsidemargin}{10mm}
\setlength{\evensidemargin}{0mm}
\setlength{\textwidth}{150mm}
\setlength{\topmargin}{0mm}
\setlength{\headheight}{0mm}
\setlength{\headsep}{0mm}
\setlength{\textheight}{230mm}
\setlength{\footskip}{15mm}
\fancyfoot{}
\fancyfootoffset{0pt}
\if@twoside
    \fancyfoot[RO,LE]{\textbf{\thepage}}
\else
    \fancyfoot[R]{\thepage}
\fi
\newlength{\title@line}


\newcommand{\ThesisTitle}[1]{{\thispagestyle{empty}\sffamily
\noindent\ifthenelse{\equal{#1}{EN}}{\ENlogo}{\CZlogo}

\vfill

\begin{center}
\LARGE\bfseries
\ifthenelse{\equal{#1}{EN}}{\TUL@nazevEN}{\TUL@nazevCZ}
\par
\end{center}

\vfill

\begin{center}
\Large\bfseries\color{\tulcolor}
\ifthenelse{\equal{#1}{EN}}{\TUL@levelEN}{\TUL@levelCZ}
\ifthenelse{\isundefined{\TUL@id}}{}{\par {\normalsize\mdseries\color{tulgray}\TUL@id}}
\end{center}

\vfill

\newcommand{\t@nd}{&}
\newcommand{\n@dpis}{\small\itshape\color{tulgray}}
\noindent\begin{tabularx}{\linewidth}{@{}lX@{}}
\ifthenelse{\equal{\TUL@kat}{Hab}}{%
  \ifthenelse{\equal{#1}{EN}}{Author}{Autor pr\'ace}: &
  \bfseries\TUL@autor \\
  \ifthenelse{\equal{#1}{EN}}{Field}{Obor}:
   & \ifthenelse{\equal{#1}{EN}}{\i@TULoborEN}{\i@TULoborCZ}
  \\
}{%
  \n@dpis \ifthenelse{\equal{#1}{EN}}{Study programme}{Studijn\'\i\ program}: &
  \ifthenelse{\equal{#1}{EN}}{\TUL@programEN}{\TUL@programCZ} \\
  \ifthenelse{\value{TUL@bor}=0}{}{%
    \n@dpis
    \ifthenelse{\equal{#1}{EN}}
        {Study branch\ifnum\theTUL@bor>1\relax es\fi}%
        {Studijn\'\i\ obor\ifnum\theTUL@bor>1\relax y\fi}:
     & \ifthenelse{\equal{#1}{EN}}{\i@TULoborEN}{\i@TULoborCZ}
    \\
    \setcounter{TULc@unter}{1}%
    \whiledo{\value{TULc@unter} < \value{TUL@bor}}{
        \stepcounter{TULc@unter}\t@nd % zamaskovan?? "&"
        \ifthenelse{\equal{#1}{EN}}
            {\@nameuse{\roman{TULc@unter}@TULoborEN}}%
            {\@nameuse{\roman{TULc@unter}@TULoborCZ}}\\
    }
  } \\[-\medskipamount]%
  \n@dpis
  \ifthenelse{\equal{#1}{EN}}{Author}{Autor pr\'ace}: &
  \bfseries\TUL@autor \\
  \n@dpis
  \ifthenelse{\equal{#1}{EN}}{Supervisor}{Vedouc\'\i\ pr\'ace}: &
  \TUL@vedouci \\
  \@ifundefined{i@TULkonzultant}{}{\n@dpis
      \ifthenelse{\equal{#1}{EN}}
          {Consultant\@ifundefined{ii@TULkonzultant}{}{s}}
          {Konzultant\@ifundefined{ii@TULkonzultant}{}{i}}:
      & \i@TULkonzultant \\}
  \setcounter{TULc@unter}{1}%
  \whiledo{\value{TULc@unter} < \value{TULk@nzult}}{
      \stepcounter{TULc@unter}\t@nd % zamaskovan?? "&"
      \@nameuse{\roman{TULc@unter}@TULkonzultant}\\
  }
}
\end{tabularx}

\vfill

\setlength{\title@line}{\linewidth}%
\addtolength{\title@line}{-1cm}%
\noindent{\color{\tulcolor}\rule{\title@line}{1pt}}%
\raisebox{-2.5pt}[0pt][0pt]\spojovaci\\
\raisebox{1pt}{Liberec \TUL@rok}
\vspace*{-2cm}
\cleardoublepage}}


\newcommand{\Assignment}{
\mbox{}
\vfill

\begin{center}
\LARGE
\ifthenelse{\equal{\TUL@nguage}{EN}}%
{This page has to be replaced\\[0.5em]by original of an assignment.}%
{Tento list nahra\v{d}te\\[0.5em]origin\'alem zad\'an\'\i.}
\thispagestyle{empty}
\end{center}

\vfill
\cleardoublepage
}

% koncovka ??ensk??ho rodu sloves
\newcommand{\TULfem}[1]{\ifthenelse{\equal{#1}{female}}{a}{}}

% vlo??en?? typu pr??ce v r??zn??ch p??dech
\newcommand{\TULpraceCZ}{\lowercase\expandafter{\TUL@levelCZ}}

\newcommand{\TULpraceou}{\ifthenelse{\equal{\TUL@kat}{BP}}{bakal\'a\v rskou pr\'aci}{%
\ifthenelse{\equal{\TUL@kat}{DP}}{diplomovou pr\'aci}{%
\ifthenelse{\equal{\TUL@kat}{Dis}}{diserta\v cn\'{\i} pr\'aci}{%
\ifthenelse{\equal{\TUL@kat}{Hab}}{habilita\v cn\'{\i} pr\'aci}{%
\ifthenelse{\equal{\TUL@kat}{Teze}}{teze diserta\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{Autoref}}{autorefer\'{a}t diserta\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{RP}}{ro\v cn\'{\i}kov\'y projekt}{%
magistersk\'y projekt}}}}}}}}

\newcommand{\TULpracee}{\ifthenelse{\equal{\TUL@kat}{BP}}{bakal\'a\v rsk\'e pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{DP}}{diplomov\'e pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{Dis}}{diserta\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{Hab}}{habilita\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{Teze}}{tez\'{\i} diserta\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{Autoref}}{autorefer\'{a}tu diserta\v cn\'{\i} pr\'ace}{%
\ifthenelse{\equal{\TUL@kat}{RP}}{ro\v cn\'{\i}kov\'eho projektu}{%
magistersk\'eho projektu}}}}}}}}

% vlo???? argument podle rodu: #1 pro projekt, #2 pro pr??ci, #3 pro teze
\newcommand{\TULpracerod}[3]{%
\ifthenelse{\equal{\TUL@kat}{RP} \OR \equal{\TUL@kat}{MP} \OR
    \equal{\TUL@kat}{Autoref}}{#1}{\ifthenelse{\equal{\TUL@kat}{Teze}}{#3}{#2}}}

\newcommand{\DeclarationCZ}[1]{
\section*{Prohl\'a\v sen\'\i}

Prohla\v{s}uji, \v{z}e \TULpracerod{sv\r uj}{svou}{sv\'e}
\TULpraceou\ jsem vypracoval\TULfem{#1} samostatn\v{e} jako p\r{u}vodn\'{\i} d\'{\i}lo
s pou\v{z}it\'{\i}m uveden\'{e} literatury%
\ifthenelse{\equal{\TUL@kat}{Hab}}{}{ a na z\'{a}klad\v{e} konzultac\'{\i} s vedouc\'{\i}m
\TULpracerod{m\'eho}{m\'e}{m\'ych} \TULpracee\ a konzultantem}.

\ifthenelse{\equal{\TUL@kat}{Hab}}{}{Jsem si v\v{e}dom\TULfem{#1} toho, \v{z}e
  na \TULpracerod{m\r uj}{mou}{m\'e} \TULpraceou\ se pln\v{e} vztahuje z\'{a}kon
  \v{c}.~121/2000 Sb., o~pr\'{a}vu autorsk\'{e}m, zejm\'{e}na \S~60~-- \v
  skoln\'{\i} d\'{\i}lo.

  Beru na v\v edom\'{\i}, \v ze Technick\'a univerzita v~Liberci nezasahuje
  do m\'ych autorsk\'ych pr\'av u\v zit\'{\i}m
  \TULpracerod{m\'eho}{m\'e}{m\'ych} \TULpracee\ pro
  vnit\v rn\'{\i} pot\v rebu Technick\'{e} univerzity v~Liberci.

  U\v ziji-li \TULpraceou\ nebo poskytnu-li licenci
  k~\TULpracerod{jeho}{jej\'{\i}mu}{jejich} vyu\v zit\'{\i},
  jsem si v\v edom\TULfem{#1} povinnosti informovat
  o~t\'eto skute\v cnosti Technickou univerzitu v~Liberci;
  v~tomto p\v r\'{\i}pad\v e m\'a Technick\'a univerzita v~Liberci
  pr\'avo ode mne po\v zadovat \'uhradu n\'aklad\r u,
  kter\'e vynalo\v zila na vytvo\v ren\'{\i} d\'{\i}la,
  a\v z do jejich skute\v cn\'e v\'y\v se.

  \ifthenelse{\equal{\TUL@kat}{BP} \OR \equal{\TUL@kat}{DP} \OR
  \equal{\TUL@kat}{Dis}}{Sou\v{c}asn\v{e} \v{c}estn\v{e} prohla\v{s}uji,
  \v{z}e text elektronick\'{e} podoby pr\'{a}ce vlo\v{z}en\'{y} do IS STAG
  se shoduje s textem ti\v{s}t\v{e}n\'{e} podoby pr\'{a}ce.}{}}

Beru na v\v{e}dom\'{\i}, \v{z}e \TULpracerod{m\r uj}{m\'a}{m\'e}
\TULpraceCZ\ \TULpracerod{bude zve\v{r}ejn\v{e}n}{bude zve\v{r}ejn\v{e}na}{budou
zve\v{r}ejn\v{e}ny} Technickou univerzitou v~Liberci v souladu s~\S~47b
z\'{a}kona \v{c}.~111/1998 Sb., o~vysok\'{y}ch \v{s}kol\'{a}ch a
o~zm\v{e}n\v{e} a dopln\v{e}n\'{\i} dal\v{s}\'{\i}ch z\'{a}kon\r{u} (z\'{a}kon
o~vysok\'{y}ch \v{s}kol\'{a}ch), ve zn\v{e}n\'{\i} pozd\v{e}j\v{s}\'{\i}ch
p\v{r}edpis\r{u}.

Jsem si v\v{e}dom\TULfem{#1} n\'{a}sledk\r{u}, kter\'{e} podle z\'{a}kona
o~vysok\'{y}ch \v{s}kol\'{a}ch mohou vypl\'{y}vat z poru\v{s}en\'{\i} tohoto
prohl\'{a}\v{s}en\'{\i}.

\vspace{2cm}
\noindent \the\day. \the\month. \the\year
\hfill
\noindent \TUL@autor
}

\newcommand{\ThesisType@EN}{\lowercase\expandafter{\TUL@levelEN}}

\newcommand{\DeclarationEN}{
\section*{Declaration}

I hereby certify, I, myself, have written my \ThesisType@EN\ as an original and
primary work using the literature listed below%
\ifthenelse{\equal{\TUL@kat}{Hab}}{}{ and consulting it with my thesis
supervisor and my thesis counsellor}.

\ifthenelse{\equal{\TUL@kat}{Hab}}{}{I acknowledge that my \ThesisType@EN\ is
  fully governed by Act No.~121/2000 Coll., the Copyright Act, in particular
  Article~60 -- School Work.

  I acknowledge that the Technical University of Liberec does not infringe my
  copyrights by using my \ThesisType@EN\ for internal purposes of the Technical
  University of Liberec.

  I am aware of my obligation to inform the Technical University of Liberec on
  having used or granted license to use the results of my \ThesisType@EN; in
  such a case the Technical University of Liberec may require reimbursement of
  the costs incurred for creating the result up to their actual amount.

  \ifthenelse{\equal{\TUL@kat}{BP} \OR \equal{\TUL@kat}{DP} \OR
  \equal{\TUL@kat}{Dis}}{At the same time, I honestly declare that the text of
  the printed version of my \ThesisType@EN\ is identical with the text of the
  electronic version uploaded into the IS STAG.}{}}

I acknowledge that the Technical University of Liberec will make my
\ThesisType@EN\ public in accordance with paragraph~47b of Act No.~111/1998
Coll., on Higher Education Institutions and on Amendment to Other Acts (the
Higher Education Act), as amended.

I am aware of the consequences which may under the Higher Education Act result
from a breach of this declaration.

\vspace{2cm}
\noindent \the\day. \the\month. \the\year
\hfill
\noindent \TUL@autor
}


\newcommand{\Declaration}[1]{\vspace*{2cm}
\thispagestyle{empty}
\begin{uzky@text}
\ifthenelse{\equal{\TUL@nguage}{EN}}{\DeclarationEN}{\DeclarationCZ{#1}}
\end{uzky@text}
\cleardoublepage
}


\newcommand{\ThesisStart}[1]{
\ifthenelse{\equal{#1}{female} \OR \equal{#1}{male}}{
    \ifthenelse{\equal{\TUL@nguage}{EN}}{\ThesisTitle{EN}}{\ThesisTitle{CZ}}
    %\ifthenelse{\equal{\TUL@kat}{BP}
    %\OR \equal{\TUL@kat}{DP}
    %\OR \equal{\TUL@kat}{RP}
    %\OR \equal{\TUL@kat}{MP}}{\Assignment}{}
    %\Declaration{#1}
    }%
{\includepdf[pages=-]{#1}}
}


\newenvironment{abstractCZ}[1][narrow]{\ifthenelse{\equal{#1}{wide}}%
{\renewcommand{\endabstractCZ}{\par}}% ??irok?? verze na konci ned??l?? nic
{\begin{uzky@text}\renewcommand{\endabstractCZ}{\end{uzky@text}}}% ??zk?? verze
\section*{\TUL@nazevCZ}\medskip\section*{Abstrakt}}{}

\newenvironment{abstractEN}[1][narrow]{\ifthenelse{\equal{#1}{wide}}%
{\renewcommand{\endabstractEN}{\par}}% ??irok?? verze na konci ned??l?? nic
{\begin{uzky@text}\renewcommand{\endabstractEN}{\end{uzky@text}}}% ??zk?? verze
\section*{\TUL@nazevEN}\medskip\section*{Abstract}}{}

\newenvironment{keywordsCZ}[1][narrow]{\ifthenelse{\equal{#1}{wide}}%
{\renewcommand{\endkeywordsCZ}{\par}}% ??irok?? verze na konci ned??l?? nic
{\begin{uzky@text}\renewcommand{\endkeywordsCZ}{\end{uzky@text}}}% ??zk?? verze
\paragraph*{Kl\'\i\v{c}ov\'a slova:}}{}

\newenvironment{keywordsEN}[1][narrow]{\ifthenelse{\equal{#1}{wide}}%
{\renewcommand{\endkeywordsEN}{\par}}% ??irok?? verze na konci ned??l?? nic
{\begin{uzky@text}\renewcommand{\endkeywordsEN}{\end{uzky@text}}}% ??zk?? verze
\paragraph*{Keywords:}}{}

%\newcommand{\keywordsCZ}[2][]{\ifthenelse{\equal{#1}{wide}}{}{\begin{uzky@text}}\noindent
%\textbf{Kl\'\i\v{c}ov\'a slova:} #2\ifthenelse{\equal{#1}{wide}}{}{\end{uzky@text}}}

\newenvironment{acknowledgement}[1][narrow]{\ifthenelse{\equal{#1}{wide}}%
{\renewcommand{\endacknowledgement}{\clearpage}}% ??irok?? pod??kov??n?? na konci jen odstr??nkuje
{\begin{uzky@text}\renewcommand{\endacknowledgement}{\end{uzky@text}\clearpage}}% ??zk??
\ifthenelse{\equal{\TUL@nguage}{EN}}%
{\section*{Acknowledgements}}%
{\section*{Pod\v ekov\'an\'{\i}}}}{}

\newenvironment{abbrList}{\ifthenelse{\equal{\TUL@nguage}{EN}}%
{\section*{List of abbreviations}\addcontentsline{toc}{section}{List of abbreviations}}%
{\section*{Seznam zkratek}\addcontentsline{toc}{section}{Seznam zkratek}}
\noindent\tabularx{\linewidth}{@{}lX@{}}}
{\endtabularx
\clearpage}

\titleformat{\chapter}%
{\normalfont\sffamily\LARGE\bfseries\color{\titlecolor}}{\thechapter}{1em}{}

