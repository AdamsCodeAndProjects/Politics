package serviceTesting;

import Entities.Comments;
import customException.CommentNotFound;
import customException.NoValueException;
import dao.imp.CommentsDAO;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.CommentsService;
import service.CommentsServiceInt;

import java.util.List;

public class CommentsServiceTests {
    static CommentsDAO commentsDAO = new CommentsDAO();
    static CommentsServiceInt commentsService = new CommentsService(commentsDAO);
    static Comments commentsOne;
    static Comments commentsTwo;
    static List<Comments> newList;
    static Comments tooLongComment;
    static Comments tooLongComment2;
    static Comments noComment;
    static Comments noCommentsTwo;




    @BeforeClass
    private static void setup() {
        commentsDAO = Mockito.mock(CommentsDAO.class);
        commentsService = new CommentsService(commentsDAO);
        commentsOne = new Comments(0, "Go there now");
        commentsOne = new Comments(1, "Go there now");
        noComment = new Comments(3,"");
        noCommentsTwo = new Comments(5, "");
        tooLongComment = new Comments(2, "jsjksjlkfsdjksdjkjkljksdajklsdjklfsdkljsdfjkl;dsfjkldsfjkldfsjklfsdjakl;jksdlfajdkflsjklsdfjkldsfajklsdfakjldsfjkldsfjklajksdlfajkdlsfajkldsfajkldsfjklfaskldjfklsadjfkl;asjdfkljasdflk;jsadfklj" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "dsak;fkds;aljfldkas;jf;ldksajfkldsajfkl;dasjfkl;dsajfkldsjfkldasjflkjdsaflkadjsflkjsadlkfjlkadsjfkladsfjkladsfjkl;ajdfskl;fjklajfkadls;jfkldaskflfdsalk;fdsakl;jfdsalkjhhajhkljsdahfsjkladhfdsalkjfhadslkfjdhasfkljdsahfakldsjfhladsfklllll;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "jkldfsakljfdsajaslk;djf;lkasdjfa;lsdkfjalksdjfl;aksdjf;laksdjflakdsjfalsk;djfl;ksadjflkdas;jfalsd;kfjads;lfjkads;lkfjads;lkfja;dlskfjald;fkjsffjkdkjslkfjdlskjfdsjflskdjflsdkjflskdjflsdkfjlsdkfjsldfkjdslfkjdslfkjsdlfkjdslfkdjslfkjsdlkfjj" +
                "jakls;fjak;ljf;lkasdjflk;sadjflk;dsajfl;kajsdflkjadsfalk;jsdf;aldskfjadsl;kjfasdlk;jfa;lsdkfjdlskfja;sdlkfjasd;fljkads;flkjfdas;lkjfdas");
        tooLongComment2 = new Comments(2, "jsjksjlkfsdjksdjkjkljksdajklsdjklfsdkljsdfjkl;dsfjkldsfjkldfsjklfsdjakl;jksdlfajdkflsjklsdfjkldsfajklsdfakjldsfjkldsfjklajksdlfajkdlsfajkldsfajkldsfjklfaskldjfklsadjfkl;asjdfkljasdflk;jsadfklj" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "dsak;fkds;aljfldkas;jf;ldksajfkldsajfkl;dasjfkl;dsajfkldsjfkldasjflkjdsaflkadjsflkjsadlkfjlkadsjfkladsfjkladsfjkl;ajdfskl;fjklajfkadls;jfkldaskflfdsalk;fdsakl;jfdsalkjhhajhkljsdahfsjkladhfdsalkjfhadslkfjdhasfkljdsahfakldsjfhladsfklllll;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "safkl;fdskl;ajfdsl;kajfdslk;ajfl;ksadjflk;sajdfkl;sajdfkljsadl;fkjasdklf;jakl;sdjflks;dajflkadsjflk;sajdf;lkasjdflk;ajflk;dsjflk;ja;fkldsjfdklasjfkl;asdjf;lsdadafjls;fladksjfkld;aj;kalfdjk;lfdjakl;fjadksl;fjlkdasjfkd;alsjfkl;dsajklf;kl;" +
                "jkldfsakljfdsajaslk;djf;lkasdjfa;lsdkfjalksdjfl;aksdjf;laksdjflakdsjfalsk;djfl;ksadjflkdas;jfalsd;kfjads;lfjkads;lkfjads;lkfja;dlskfjald;fkjsffjkdkjslkfjdlskjfdsjflskdjflsdkjflskdjflsdkfjlsdkfjsldfkjdslfkjdslfkjsdlfkjdslfkdjslfkjsdlkfjj" +
                "jakls;fjak;ljf;lkasdjflk;sadjflk;dsajfl;kajsdflkjadsfalk;jsdf;aldskfjadsl;kjfasdlk;jfa;lsdkfjdlskfja;sdlkfjasd;fljkads;flkjfdas;lkjfdas");
    }

    @Test(expectedExceptions = CommentNotFound.class)
    public void badIdForCommentMockito() {
        Mockito.when(commentsDAO.getCommentById(1)).thenThrow(new CommentNotFound("Comment not found"));
        commentsService.getCommenetByIdService(2);
    }

    @Test(expectedExceptions = NoValueException.class)
    public void noCommentMockito() {
        Mockito.when(commentsDAO.createComment(noComment)).thenReturn(noCommentsTwo);
        commentsService.createCommentService(noComment);
    }

    @Test
    public void getAllComments() {
        Mockito.when(commentsDAO.getAllComments()).thenReturn(newList);
        List<Comments> result = commentsService.getAllCommentsService();
        Assert.assertEquals(result, newList);
    }
}
