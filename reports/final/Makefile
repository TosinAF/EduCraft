all: report.pdf

report.pdf: report.tex introduction.tex requirements.tex design.tex implementation.tex testing-analyis.tex reflection.tex
	pdflatex -output-directory out report.tex
	bibtex out/report.aux
	pdflatex -output-directory out report.tex report.tex
	pdflatex -output-directory out report.tex report.tex
	mv out/report.pdf .

clean:
	rm *.log
	rm *.aux
	rm *.bbl
	rm *.blg
	rm *.out
	rm *.lof
