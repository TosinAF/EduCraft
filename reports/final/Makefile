ROOT = report.tex
CHAPTERS = introduction.tex requirements.tex design.tex implementation.tex testing.tex reflection.tex
APPENDICES = unittests.tex measures.tex
BIBLIOGRAPHY = bibliography.bib

all: report.pdf

report.pdf: report.tex $(CHAPTERS) $(APPENDICES) $(BIBLIOGRAPHY)
	pdflatex report.tex
	bibtex report.aux
	pdflatex report.tex
	pdflatex report.tex

clean:
	rm *.log
	rm *.aux
	rm *.bbl
	rm *.blg
	rm *.out
	rm *.lof
