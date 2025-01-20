package szczerkowska.uken.projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.websocket.server.PathParam;
import szczerkowska.uken.projekt.model.ProjektMovie;
import szczerkowska.uken.projekt.repository.ProjektMovieRepository;

@Controller
@RequestMapping("/movies")
public class ProjektMovieController {
    
    @Autowired
    private ProjektMovieRepository movieRepository;

    @GetMapping("/")
    public String showMovies(Model model){
        List<ProjektMovie> movie = movieRepository.findAll();
        if (movie.isEmpty()){
            return "empty";
        }
        model.addAttribute("movies", movie);
        return "moviesList";
    }

    @GetMapping("/new")
    public String showAddForm(Model model){
        ProjektMovie m = new ProjektMovie();
        model.addAttribute("movie", m);
        return "movieAdd";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute("movie") ProjektMovie movie, BindingResult bind){
        if (bind.hasErrors()){
            return "movieAdd";
        }
        movieRepository.save(movie);
        return "redirect:/movies/";
    }

    @GetMapping("/del/{id}")
    public String delMovie(@PathVariable("id") Long id, Model model){
        if (id != null){
            ProjektMovie m = movieRepository.findById(id).orElse(null);
            model.addAttribute("movie", m);
            return "movieDel";
        }else{
            return "moviesList";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id, @PathParam("accept") Boolean accept){
        if (id != null){
            ProjektMovie m = movieRepository.findById(id).orElse(null);
            if (m != null){
                movieRepository.delete(m);
            }
        }
        return "redirect:/movies/";
    }
}
