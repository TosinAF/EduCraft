all: report.pdf

report.pdf: report.tex
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
