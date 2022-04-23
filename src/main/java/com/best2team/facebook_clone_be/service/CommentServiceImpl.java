package com.best2team.facebook_clone_be.service;


import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.dto.CommentResponseDto;
import com.best2team.facebook_clone_be.model.Comment;
import com.best2team.facebook_clone_be.model.Post;
import com.best2team.facebook_clone_be.repository.CommentRepository;
import com.best2team.facebook_clone_be.repository.PostRepository;
import com.best2team.facebook_clone_be.repository.UserRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //댓글 작성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다!")
        );
        Validator.emptyComment(requestDto);

        String content = requestDto.getComment();

        Comment comment = new Comment(content, userDetails.getUser().getUserId(), post);
        commentRepository.save(comment);


        return new CommentResponseDto(post.getPostId(),comment.getCommentId(),comment.getContent(),
                userRepository.findById(comment.getUserId()).orElseThrow(IllegalArgumentException::new).getUserName(),comment.getUserId(),comment.getCreatedAt());

    }

    //댓글 수정
    @Transactional
    public void updateComment(long commentid, CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다!")
        );
        comment.update(requestDto);
    }

    //댓글 삭제
    public void deleteComment(long commentid, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.!")
        );
        if(!Objects.equals(comment.getUserId(), userDetails.getId())){

            throw new IllegalArgumentException("댓글 삭제가 실패하였습니다.");
        }

        commentRepository.deleteById(commentid);
    }


    public Page<CommentResponseDto> getCommentList(Long postid, int pageno) {

        List<Comment> commentList= commentRepository.findAllByPostOrderByCreatedAtDesc(postRepository.findById(postid).orElseThrow(IllegalArgumentException::new));
        Pageable pageable = getPageable(pageno);

        List<CommentResponseDto> commentPageList = new ArrayList<>();
        forboardList(commentList, commentPageList);

        int start = pageno * 5;
        int end = Math.min((start + 5),commentList.size());

        Page<CommentResponseDto> page = new PageImpl<>(commentPageList.subList(start, end), pageable, commentPageList.size());
        return page;
    }

    private Pageable getPageable(int page) {
        Sort.Direction direction = Sort.Direction.DESC ;
        Sort sort = Sort.by(direction, "id");
        return PageRequest.of(page, 5,sort);
    }

    //보드리스트 만들기
    private void forboardList(List<Comment> boardList, List<CommentResponseDto> commentPageList) {
        for (Comment comment : boardList) {

            CommentResponseDto boardResponseDto = new CommentResponseDto(comment.getCommentId(), comment.getPost().getPostId(), comment.getContent(),
                    userRepository.findById(comment.getUserId()).orElseThrow(IllegalArgumentException::new).getUserName(),
                    comment.getUserId(), comment.getCreatedAt());
            commentPageList.add(boardResponseDto);
        }
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public String nullex(IllegalArgumentException e) {
        return e.getMessage();
    }
}
