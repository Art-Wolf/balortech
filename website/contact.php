<?php

require_once 'includes/top.php';

?>
               <!-- Newsletter form : Keep the id : newsletter on form--> 
<?php
if (isset($_GET['name']))
//if "email" is filled out, send email
  {
  //send email
  $email = strip_tags($_GET['email']) ;
  $subject = "Contact Form";
  $message = "Name: " . strip_tags($_GET['name']);
  $message .= " Email Address: " . $email;
  $message .= " Message: " . strip_tags($_GET['message']);

  mail("contact@balortech.com", $subject,
  $message, "From:" . $email);
?>
               <form class="form-horizontal" id="newsletter" >
                  <p>Thank you for contacting Balor LLC. Someone will reply to you as soon as possible.</p>
               </form>
<?php
  }
else
//if "email" is not filled out, display the form
  {
?>

               <form class="form-horizontal" id="newsletter" action="mail.php">
                  <div class="control-group">
                     <div class="controls"> <input type="email" name="email" placeholder="Enter your email address to be notified"> <input type="submit" value="Go !"> </div>
                  </div>
               </form>
<?php
   }
?>
               <!-- end #newsletter -->
<?php

require_once 'includes/footer.php';

?>