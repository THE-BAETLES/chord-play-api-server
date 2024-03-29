package com.chordplay.chordplayapiserver.domain.sheetLike.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetLikeRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheetLike.exception.SheetLikeNotFoundException;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SheetLikeServiceImpl implements SheetLikeService {

    private final SheetLikeRepository sheetLikeRepository;
    private final SheetService sheetService;
    private final UserService userService;

    @Override
    public void addLike(String sheetId) {

        Sheet sheet = sheetService.getSheet(sheetId);
        User user = userService.getUser(ContextUtil.getPrincipalUserId());
        SheetLike sheetLike = new SheetLike(user,sheet,LocalDateTime.now());
        sheetLikeRepository.save(sheetLike);
    }

    @Override
    public void deleteLike(String sheetId) {
        Sheet sheet = sheetService.getSheet(sheetId);
        User user = userService.getUser(ContextUtil.getPrincipalUserId());
        sheetLikeRepository.deleteBySheetAndUser(sheet, user).orElseThrow(()-> new SheetLikeNotFoundException());
    }
}
