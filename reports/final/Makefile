ROOT = report.tex
CHAPTERS = introduction.tex requirements.tex design.tex implementation.tex testing.tex reflection.tex
APPENDICES = classdiagram.tex unittests.tex measures.tex userguide.tex minutes.tex
BIBLIOGRAPHY = bibliography.bib

all: gp13-pxb1-fgr.pdf

gp13-pxb1-fgr.pdf: report.pdf
	mv $< $@

report.pdf: report.tex $(CHAPTERS) $(APPENDICES) $(BIBLIOGRAPHY)
	pdflatex $<
	bibtex report.aux
	pdflatex $<
	pdflatex $<

clean:
	rm *.log
	rm *.aux
	rm *.bbl
	rm *.blg
	rm *.out
	rm *.lof
