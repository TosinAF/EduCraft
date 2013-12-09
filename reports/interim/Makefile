all: report.pdf

report.pdf: report.tex introduction.tex requirements.tex design-implementation.tex prototypes.tex progress.tex time-plan.tex
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
