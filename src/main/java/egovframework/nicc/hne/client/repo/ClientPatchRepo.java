package egovframework.nicc.hne.client.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.nicc.hne.client.model.entity.ClientPatch;
import egovframework.nicc.hne.client.model.enumeration.PatchType;

public interface ClientPatchRepo extends JpaRepository<ClientPatch, Integer> {

	List<ClientPatch> findAllByOrderByPatchAndroidVersionDesc();

  List<ClientPatch> findByPatchTypeOrderByPatchAndroidVersionDesc(PatchType patchType);

	List<ClientPatch> findByPatchType(PatchType patchType);
}
