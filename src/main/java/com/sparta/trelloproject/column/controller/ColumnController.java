//package com.sparta.trelloproject.column.controller;
//
//import com.sparta.trelloproject.column.dto.ColumnRequestDto;
//import com.sparta.trelloproject.column.dto.ColumnResponseDto;
//import com.sparta.trelloproject.column.service.ColumnService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/boards")
//public class ColumnController {
//    private final ColumnService columnService;
//
//    @PostMapping("/{boardId}/columns")
//    public ResponseEntity<ApiResponseDto> createColumn(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                                       @PathVariable("boardId") Long boardId,
//                                                       @RequestBody ColumnRequestDto requestDto) {
//        columnService.createColumn(userDetails.getUser(), boardId, requestDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("컬럼 생성 완료", HttpStatus.CREATED.value()));
//    }
//
//    @GetMapping("/{boardId}/columns")
//    public ResponseEntity<List<ColumnResponseDto>> getColumn(@PathVariable("boardId") Long boardId) {
//        List<ColumnResponseDto> columnList = columnService.getColumn();
//        return ResponseEntity.ok().body(columnList);
//    }
//
//    @PutMapping("/{boardId}/columns/{columnId}")
//    public ResponseEntity<ApiResponseDto> updateColumn(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                                       @RequestBody ColumnRequestDto requestDto,
//                                                       @PathVariable("boardId") Long boardId,
//                                                       @PathVariable("columnId") Long columnId) {
//        columnService.updateColumn(userDetails.getUser(), requestDto, boardId, columnId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("컬럼 수정 완료", HttpStatus.OK.value()));
//    }
//
//    @DeleteMapping("/{boardId}/columns/{columnId}")
//    public ResponseEntity<ApiResponseDto> deleteColumn(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                                       @PathVariable("boardId") Long boardId,
//                                                       @PathVariable("columnId") Long columnId) {
//        columnService.deleteColumn(userDetails.getUser(), boardId, columnId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("컬럼 삭제 완료", HttpStatus.OK.value()));
//    }
//}