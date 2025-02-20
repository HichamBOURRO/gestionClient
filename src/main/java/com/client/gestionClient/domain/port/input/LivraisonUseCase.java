package com.client.gestionClient.domain.port.input;

import com.client.gestionClient.domain.model.LivraisonModel;
import java.util.List;

public interface LivraisonUseCase {
    public LivraisonModel addLivraison(LivraisonModel livraison);
    public void deleteLivraison(Long id);
    public LivraisonModel getLivraisonById(Long id);
    public LivraisonModel getLivraisonByIdAndEmail(Long id,String email);
    public List<LivraisonModel> getLivraisonsByEmail(String email);
    public List<LivraisonModel> getLivraisons();

}
