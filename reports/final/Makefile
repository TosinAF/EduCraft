all: report.pdf

report.pdf: report.tex introduction.tex requirements.tex design.tex implementation.tex testing.tex reflection.tex measures.tex
	pdflatex report.tex
	bibtex report.aux
	pdflatex report.tex report.tex
	pdflatex report.tex report.tex

clean:
	rm *.log
	rm *.aux
	rm *.bbl
	rm *.blg
	rm *.out
	rm *.lof
